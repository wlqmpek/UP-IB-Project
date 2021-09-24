package com.projekat.UPIB.support.xml_parser;

import com.projekat.UPIB.models.ZdravstveniKarton;
import com.projekat.UPIB.models.ZdravstveniKartonEncrypted;
import com.projekat.UPIB.security.EnkripcijaDekripcijaUtils;
import com.projekat.UPIB.services.IZdravstveniKartonEncrypted;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ZdravstveniKartonRepo {

    @Autowired
    private XMLParser xmlParser;

    @Autowired
    private IZdravstveniKartonEncrypted kartonEncrypted;

    @Autowired
    private EnkripcijaDekripcijaUtils enkripcijaDekripcijaUtils;

    public ZdravstveniKarton findZKForUser(String email) throws ParserConfigurationException, IOException, SAXException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        List<ZdravstveniKarton> zdravstveniKartoni = xmlParser.xmlToZK(kartonEncrypted.findAll().toString());
        for (ZdravstveniKarton zdravstveniKarton : zdravstveniKartoni) {
            if(zdravstveniKarton.getPacijent().getEmailKorisnika().equalsIgnoreCase(email)){
                return zdravstveniKarton;
            }
        }
        return null;
    }

    public void saveZK(ZdravstveniKarton zdravstveniKarton) throws ParserConfigurationException, IOException, SAXException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        List<ZdravstveniKarton> zdravstveniKartoni;
        try {
            zdravstveniKartoni = xmlParser.xmlToZK(kartonEncrypted.findAll().getEncrypted());
        } catch (Exception e){
            zdravstveniKartoni = new ArrayList<>();
        }
        zdravstveniKartoni.add(zdravstveniKarton);
        String xml = xmlParser.parseToXML(zdravstveniKartoni);
        String encrypted = enkripcijaDekripcijaUtils.enkriptujZdravstveniKarton(xml);

        kartonEncrypted.save(new ZdravstveniKartonEncrypted(1L, encrypted));
    }
}

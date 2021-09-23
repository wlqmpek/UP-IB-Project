package com.projekat.UPIB.support.xml_parser;

import com.projekat.UPIB.models.ZdravstveniKarton;
import com.projekat.UPIB.security.EnkripcijaDekripcijaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

@Component
public class XMLParser {

    @Autowired
    private EnkripcijaDekripcijaUtils enkripcijaDekripcijaUtils;

    public List<ZdravstveniKarton> xmlToZK(String enc) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        String decrypted = enkripcijaDekripcijaUtils.dekriptujZdravstveniKarton(enc);

        SAXParser saxParser = factory.newSAXParser();
        ZdravstveniKartonHandler zdravstveniKartonHandler = new ZdravstveniKartonHandler();
        saxParser.parse(decrypted, zdravstveniKartonHandler);
        List<ZdravstveniKarton> zdravstveniKartoni = zdravstveniKartonHandler.getZdravstveniKartoni();
        return zdravstveniKartoni;
    }

    public String parseToXML(List<ZdravstveniKarton> zdravstveniKartoni){

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<ZDRAVSTVENI_KARTONI>");
        for (ZdravstveniKarton zdravstveniKarton : zdravstveniKartoni) {
            stringBuilder.append("<ZDRAVSTVENI_KARTON ID="+zdravstveniKarton.getIdZdravstvenogKartona()+">");
            stringBuilder.append("<VISINA>"+zdravstveniKarton.getVisina()+"</VISINA>");
            stringBuilder.append("<TEZINA>"+zdravstveniKarton.getTezina()+"</TEZINA>");
            stringBuilder.append("<KRVNA_GRUPA>"+zdravstveniKarton.getKrvnaGrupa()+"</KRVNA_GRUPA>");
            stringBuilder.append("<DIOPTRIJA>"+zdravstveniKarton.getDioptrija()+"</DIOPTRIJA>");
            stringBuilder.append("<ALERGIJE>"+zdravstveniKarton.getAlergije()+"</ALERGIJE>");
            stringBuilder.append("<PACIJENT>"+zdravstveniKarton.getPacijent().getEmailKorisnika()+"</PACIJENT>");
            stringBuilder.append("</ZDRAVSTVENI_KARTON>");
        }
        stringBuilder.append("</ZDRAVSTVENI_KARTONI>");

        return stringBuilder.toString();
    }
}

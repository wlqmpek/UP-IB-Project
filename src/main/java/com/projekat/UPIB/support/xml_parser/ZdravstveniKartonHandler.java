package com.projekat.UPIB.support.xml_parser;

import com.projekat.UPIB.models.ZdravstveniKarton;
import com.projekat.UPIB.services.IPacijentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class ZdravstveniKartonHandler extends DefaultHandler {

    private List<ZdravstveniKarton> zdravstveniKartoni = new ArrayList<>();

    private ZdravstveniKarton zdravstveniKarton;
    private String text;

    @Autowired
    private IPacijentService pacijentService;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equalsIgnoreCase("ZDRAVSTVENI_KARTON")){
            zdravstveniKarton = new ZdravstveniKarton();
            String id = attributes.getValue("ID");
            zdravstveniKarton.setIdZdravstvenogKartona(Long.parseLong(id));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equalsIgnoreCase("ZDRAVSTVENI_KARTON")){
            zdravstveniKartoni.add(zdravstveniKarton);
            zdravstveniKarton = null;
        } else if(qName.equalsIgnoreCase("VISINA")){
            zdravstveniKarton.setVisina(Integer.parseInt(text));
        } else if(qName.equalsIgnoreCase("TEZINA")){
            zdravstveniKarton.setTezina(Integer.parseInt(text));
        } else if(qName.equalsIgnoreCase("KRVNA_GRUPA")){
            zdravstveniKarton.setKrvnaGrupa(text);
        } else if(qName.equalsIgnoreCase("DIOPTRIJA")){
            zdravstveniKarton.setDioptrija(Double.parseDouble(text));
        } else if(qName.equalsIgnoreCase("ALERGIJE")){
            zdravstveniKarton.setAlergije(text);
        } else if(qName.equalsIgnoreCase("PACIJENT")){
            zdravstveniKarton.setPacijent(pacijentService.findPacijentByEmailKorisnika(text));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text = new String(ch, start, length);
    }

    public List<ZdravstveniKarton> getZdravstveniKartoni(){return  zdravstveniKartoni;}
    public void setZdravstveniKartoni(List<ZdravstveniKarton> zdravstveni){this.zdravstveniKartoni = zdravstveni;}
}

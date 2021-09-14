package com.projekat.UPIB.services.implementation;

import java.time.LocalDateTime;
import java.util.*;

import com.projekat.UPIB.models.Pregled;
import com.projekat.UPIB.web.dto.ParametriPretrageKlinikaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projekat.UPIB.models.Klinika;
import com.projekat.UPIB.repositories.KlinikaRepozitorijum;
import com.projekat.UPIB.services.IKlinikaService;


//TODO: Pomocu Criteria API ili QueryDLS implementirati search. (docs.spring.io) WLQMPEK
@Service
public class KlinikaService implements IKlinikaService{

	@Autowired
    private KlinikaRepozitorijum klinikaRepozitorijum;

	@Autowired
    private OceneKlinikeService oceneKlinikeService;

    //Mozda treba da se zameni sa .findById(id); //WLQMPEK
    //Razlika izmedju dve metode je sto getOne radi lazyLoding dok findById skida ceo Objekat.
    @Override
    public Klinika findOne(Long id) {
        return klinikaRepozitorijum.getOne(id);
    }

    @Override
    public List<Klinika> findAll() {
        return klinikaRepozitorijum.findAll();
    }

    @Override
    public Klinika save(Klinika klinika) {
        return klinikaRepozitorijum.save(klinika);
    }

    @Override
    public List<Klinika> findKlinikasByAdresaContaining(String adresa) {
        return klinikaRepozitorijum.findKlinikasByAdresaContaining(adresa);
    }

    @Override
    public void remove(Long id) {
    	Klinika klinika = klinikaRepozitorijum.getOne(id);
    	klinikaRepozitorijum.delete(klinika);
    }

    //Pretraga klinika po parametrima pretrage. - WLQ
    //Koristi se kad pacijent pretrazuje klinike.
    //TODO: Seti se da kad filtriras po pregledima proveris da li je pregled slobodan ili nije.
    @Override
    public Set<Klinika> pretragaKlinika(ParametriPretrageKlinikaDto parametriPretrageKlinikaDto) {
        List<Klinika> sveKlinike = findAll();
        List<Klinika> filtriraneKlinike1 = new ArrayList<>();
        Set<Klinika> filtriraneKlinike2 = new HashSet<>();
        LocalDateTime pocetakDana = parametriPretrageKlinikaDto.getDatum().atStartOfDay();
        LocalDateTime krajDana = parametriPretrageKlinikaDto.getDatum().atStartOfDay().plusHours(24);
        for(Klinika klinika:sveKlinike) {
            if(klinika.getAdresa().toLowerCase().contains(parametriPretrageKlinikaDto.getAdresa().toLowerCase()) && oceneKlinikeService.avgOcena(klinika.getIdKlinike()) >= parametriPretrageKlinikaDto.getOcena()) {
                filtriraneKlinike1.add(klinika);
            }
        }
        for(Klinika klinika:filtriraneKlinike1) {
            for(Pregled pregled:klinika.getSlobodniTermini()) {
                if(pregled.getPocetakTermina().isAfter(pocetakDana) && pregled.getPocetakTermina().isBefore(krajDana) && pregled.getZdravstveniKarton() == null) {
                    filtriraneKlinike2.add(pregled.getKlinika());
                }
            }
        }
        return filtriraneKlinike2;
    }



}

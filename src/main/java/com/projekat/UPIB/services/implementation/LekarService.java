package com.projekat.UPIB.services.implementation;

import com.projekat.UPIB.models.Lekar;
import com.projekat.UPIB.models.Pregled;
import com.projekat.UPIB.repositories.LekarRepozitorijum;
import com.projekat.UPIB.repositories.OceneLekaraRepozitorijum;
import com.projekat.UPIB.services.ILekarService;
import com.projekat.UPIB.services.IOceneLekaraService;
import com.projekat.UPIB.support.converters.lekar.LekarToLekarSaPregledimaFrontConverter;
import com.projekat.UPIB.web.dto.lekar.LekarSaPregledimaFrontDto;
import com.projekat.UPIB.web.dto.pregled.PregledPretrageFrontDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class LekarService implements ILekarService {

    @Autowired
    private LekarRepozitorijum lekarRepozitorijum;

    @Autowired
    private PregledService pregledService;

    @Autowired
    private LekarToLekarSaPregledimaFrontConverter ltlspfc;

    @Autowired
    private IOceneLekaraService oceneLekaraService;

    //Mozda treba da se zameni sa .findById(id); //WLQMPEK
    //Razlika izmedju dve metode je sto getOne radi lazyLoding dok findById skida ceo Objekat.
    @Override
    public Lekar findOne(Long id) {
        return lekarRepozitorijum.getOne(id);
    }

    @Override
    public Lekar findLekarByEmailKorisnika(String email) {
        return lekarRepozitorijum.findLekarByEmailKorisnika(email);
    }

    @Override
    public List<Lekar> findAll() {
        return lekarRepozitorijum.findAll();
    }

    @Override
    public Lekar save(Lekar lekar) {
        return lekarRepozitorijum.save(lekar);
    }

    @Override
    public void remove(Long id) {

        Lekar lekar = lekarRepozitorijum.getOne(id);
        lekarRepozitorijum.delete(lekar);
    }

    @Override
    public List<Lekar> findLekarByKlinika(Long id) {
        return lekarRepozitorijum.findLekarByKlinikaIdKlinike(id);
    }

    @Override
    public Set<LekarSaPregledimaFrontDto> pretragaLekaraPoKliniciISlobodnomTerminu(Long idKlinike, LocalDate date) {

        Set<LekarSaPregledimaFrontDto> rezultat = new HashSet<>();

        List<Lekar> sviLekari = findLekarByKlinika(idKlinike);
        //Definisemo vremenski opseg od unetog datuma.
        LocalDateTime pocetakDana = date.atStartOfDay();
        LocalDateTime krajDana = date.atStartOfDay().plusHours(24);

        for(Lekar lekar:sviLekari) {
            List<Pregled> preglediLekara = pregledService.findByLekarIdKorisnika(lekar.getIdKorisnika());
            List<Pregled> slobodniPreglediLekara = new ArrayList<>();
            for(Pregled pregled:preglediLekara) {
                if(pregled.getPocetakTermina().isAfter(pocetakDana) && pregled.getPocetakTermina().isBefore(krajDana) && pregled.getZdravstveniKarton() == null) {
                    slobodniPreglediLekara.add(pregled);
                }
            }
            if(slobodniPreglediLekara.size() != 0) {
                rezultat.add(ltlspfc.convert(lekar, slobodniPreglediLekara));
            }
        }
        return rezultat;
    }

    public Double prosecnaOcenaLekara(Long id) {
        Double prosecnaocena = oceneLekaraService.avgOcena(id);
        prosecnaocena = (prosecnaocena == null) ? 0.0 : prosecnaocena;
        return prosecnaocena;
    }
}

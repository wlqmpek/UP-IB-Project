package com.projekat.UPIB.services.implementation;

import java.time.LocalDate;
import java.util.List;

import com.projekat.UPIB.mail.EmailService;
import com.projekat.UPIB.models.Pacijent;
import com.projekat.UPIB.support.exceptions.PregledVecZakazanException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projekat.UPIB.models.Pregled;
import com.projekat.UPIB.repositories.PregledRepozitorijum;
import com.projekat.UPIB.services.IPregledService;

@Service
public class PregledService implements IPregledService{

	@Autowired
    private PregledRepozitorijum pregledRepozitorijum;

	@Autowired
    private PacijentService pacijentService;

	@Autowired
    private EmailService emailService;

    @Override
    public Pregled findOne(Long id) {
        return pregledRepozitorijum.getOne(id);
    }

    @Override
    public List<Pregled> findAll() {
        return pregledRepozitorijum.findAll();
    }

    @Override
    public Pregled save(Pregled pregled) {
        return pregledRepozitorijum.save(pregled);
    }

    @Override
    public void remove(Long id) {
    	Pregled pregled = pregledRepozitorijum.getOne(id);
    	pregledRepozitorijum.delete(pregled);
    }

    @Override
    public List<Pregled> findAllByKlinika(Long id) {
        return pregledRepozitorijum.findByKlinikaIdKlinike(id);
    }

    @Override
    public List<Pregled> findByLekarIdKorisnika(Long id) {
        return pregledRepozitorijum.findPregledsByLekarIdKorisnika(id);
    }

    //Koristi se za pronalazenje pregleda odredjene klinike od nekog odredjenog datuma. - WLQ
    public List<Pregled> findPregledsByPocetakTerminaIsOrPocetakTerminaIsAfterAndKlinikaIdKlinike(Long idKlinike, LocalDate datum) {
        return pregledRepozitorijum.findPregledsByPocetakTerminaIsOrPocetakTerminaIsAfterAndKlinikaIdKlinike(datum.atStartOfDay(), datum.atStartOfDay(), idKlinike);
    }

    @Override
    public void zakaziPregled(Long idKorisnika, Long idPregleda) {
        Pacijent pacijent = pacijentService.findOne(idKorisnika);
        String subject = "Potvrda termina";
        String message = String.format("Molimo vas pretisnite na link kako bi ste potvrdili rezervaciju termina. \n " +
                "https://localhost:3000/potvrda-termina/idKorisnika/%d/idTermina/%d", idKorisnika, idPregleda);

        emailService.sendMessage(pacijent.getEmailKorisnika(), subject, message);
    }

    @Override
    public Pregled potvrdiPregled(Long idKorisnika, Long idPregleda) {
        Pacijent pacijent = pacijentService.findOne(idKorisnika);
        Pregled pregled = findOne(idPregleda);

        if(pregled.getZdravstveniKarton() != null) {
            throw new PregledVecZakazanException();
        }
        pregled.setZdravstveniKarton(pacijent.getZdravstveniKarton());
        save(pregled);
        return pregled;
    }
}

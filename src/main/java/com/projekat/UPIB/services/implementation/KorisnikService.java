package com.projekat.UPIB.services.implementation;

import com.projekat.UPIB.models.Korisnik;
import com.projekat.UPIB.repositories.AdminRepozitorijum;
import com.projekat.UPIB.repositories.LekarRepozitorijum;
import com.projekat.UPIB.repositories.PacijentRepozitorijum;
import com.projekat.UPIB.services.IKorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class KorisnikService implements IKorisnikService {

    @Autowired
    LekarRepozitorijum lekarRepozitorijum;

    @Autowired
    PacijentRepozitorijum pacijentRepozitorijum;

    @Autowired
    AdminRepozitorijum adminRepozitorijum;

    //TODO: Dodati za sestru <3 - WLQ
//    @Autowired
//    private AuthenticationManager authenticationManager;

    // Funkcija koja na osnovu email-a iz baze vraca objekat User-a
    @Override
    public Korisnik findKorisnikByEmailKorisnika(String emailKorisnika) {
        System.out.println("Prosledjen email je " +emailKorisnika);
        Korisnik korisnik = null;
        korisnik = adminRepozitorijum.findAdministratorByEmailKorisnika(emailKorisnika);

        if(korisnik == null)
            korisnik = lekarRepozitorijum.findLekarByEmailKorisnika(emailKorisnika);
        if(korisnik == null)
            korisnik = pacijentRepozitorijum.findPacijentByEmailKorisnika(emailKorisnika);
//        if(korisnik == null)
//            korisnik = medicinskaSestraRepozitorijum.findMedicinskaSestraByEmailKorisnika(emailKorisnika);

        System.out.println("Nadjen korisnik je " + korisnik);

        return korisnik;
    }


}

package com.projekat.UPIB.repositories;

import com.projekat.UPIB.models.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KorisnikRepozitorijum extends JpaRepository<Korisnik, Long> {

    Korisnik findKorisnikByEmailKorisnika(String emailKorisnika);
}

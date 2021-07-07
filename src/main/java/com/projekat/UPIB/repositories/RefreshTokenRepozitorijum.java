package com.projekat.UPIB.repositories;

import com.projekat.UPIB.models.Korisnik;
import com.projekat.UPIB.models.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepozitorijum extends JpaRepository<RefreshToken, Long> {

    @Override
    Optional<RefreshToken> findById(Long id);

    Optional<RefreshToken> findByToken(String token);

    void removeByKorisnik(Korisnik korisnik);

}

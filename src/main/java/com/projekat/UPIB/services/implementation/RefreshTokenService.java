package com.projekat.UPIB.services.implementation;

import com.projekat.UPIB.models.RefreshToken;
import com.projekat.UPIB.repositories.KorisnikRepozitorijum;
import com.projekat.UPIB.repositories.RefreshTokenRepozitorijum;
import com.projekat.UPIB.support.exceptions.TokenRefreshException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Value("6000000")
    private Long refreshTokenDurationMs;

    @Autowired
    private RefreshTokenRepozitorijum refreshTokenRepozitorijum;

    @Autowired
    private KorisnikRepozitorijum korisnikRepozitorijum;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepozitorijum.findByToken(token);
    }

    public RefreshToken createRefreshToken(Long userId) {
        System.out.println("Generate refresh token");
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setKorisnik(korisnikRepozitorijum.findById(userId).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepozitorijum.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepozitorijum.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }

    @Transactional
    public void deleteByIdKorisnika(Long idKorisnika) {
        refreshTokenRepozitorijum.removeByKorisnik(korisnikRepozitorijum.findById(idKorisnika).get());
    }
}

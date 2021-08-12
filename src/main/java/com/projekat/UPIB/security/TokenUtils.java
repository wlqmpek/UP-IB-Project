package com.projekat.UPIB.security;

import com.projekat.UPIB.models.Korisnik;
import com.projekat.UPIB.models.RefreshToken;
import com.projekat.UPIB.services.implementation.RefreshTokenService;
import com.projekat.UPIB.support.exceptions.TokenRefreshException;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class TokenUtils {

    private static final Logger logger = LoggerFactory.getLogger(TokenUtils.class);

    @Autowired
    private RefreshTokenService refreshTokenService;

    // Izdavac tokena
    @Value("UP-IB-Project")
    private String APP_NAME;

    // Tajna koju samo backendaplikacija treba da zna kako bi mogla da generise i proveri JWT https://jwt.io/
    @Value("myXAuthSecret")
    private String jwtSecret;

    // Period vazenja
    //Promenio zbog principala
    @Value("6000")
    private Long jwtExpirationMs;

    // Naziv headera kroz koji ce se prosledjivati JWT u komunikaciji server-klijent
    @Value("Authorization")
    private String AUTH_HEADER;

    private static final String AUDIENCE_WEB = "web";

    // Algoritam za potpisivanje JWT
    private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    public String generateJwtToken(Authentication authentication) {
        Korisnik userPrincipal = (Korisnik) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .claim("roles", userPrincipal.getAuthorities().get(0).getImeAuthority())
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String generateJwtToken(String refreshTokenString) {
        RefreshToken refreshToken = refreshTokenService.findByToken(
                refreshTokenString).orElseThrow(() -> new TokenRefreshException(refreshTokenString,
                "Refresh token is not in database!"));
        Korisnik korisnik = refreshToken.getKorisnik();
        return Jwts.builder()
                .setSubject((korisnik.getEmailKorisnika()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .claim("roles", korisnik.getAuthorities().get(0).getImeAuthority())
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String generateTokenFromEmail(String email) {
        return Jwts.builder().setSubject(email).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)).signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    
    public String generateTokenFromEmail(String email, int duration) {
        return Jwts.builder().setSubject(email).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + (jwtExpirationMs*duration))).signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getEmailFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature: {}" + e.getMessage());
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT signature: {}" + e.getMessage());
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("Invalid JWT signature: {}" + e.getMessage());
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("Invalid JWT signature: {}" + e.getMessage());
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid JWT signature: {}" + e.getMessage());
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

}

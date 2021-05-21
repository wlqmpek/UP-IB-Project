package com.projekat.UPIB.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtils {

    // Izdavac tokena
    @Value("UP-IB-Project")
    private String APP_NAME;

    // Tajna koju samo backendaplikacija treba da zna kako bi mogla da generise i proveri JWT https://jwt.io/
    @Value("myXAuthSecret")
    private String SECRET;

    // Period vazenja
    @Value("300000")
    private Long EXPIRES_IN;

    // Naziv headera kroz koji ce se prosledjivati JWT u komunikaciji server-klijent
    @Value("Authorization")
    private String AUTH_HEADER;

    private static final String AUDIENCE_WEB = "web";

    // Algoritam za potpisivanje JWT
    private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;


    public String generateToken(String email, String roles) {
        System.out.println("Rola korisnika je " + roles + "!");
        return Jwts.builder()
                .setIssuer(APP_NAME)
                .setSubject(email)
                .setAudience(AUDIENCE_WEB)
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate())
                .claim("roles", roles)
                .signWith(SIGNATURE_ALGORITHM, SECRET).compact();
    }

    private Date generateExpirationDate() { return new Date(new Date().getTime() + EXPIRES_IN); }



    public String getEmailFromToken(String token) {
        String email;
        try {
            final Claims claims = getClaimsFromToken(token);
            //Ovde nece funkcionisati, name treba jebani mail. - WLQ
            email = claims.getSubject();
        } catch (Exception e) {
            email = null;
        }
        return email;
    }

    public Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = (Claims) Jwts.parser().setSigningKey(this.SECRET).
                    parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expirationDate;
        try {
            Claims claims = getClaimsFromToken(token);
            expirationDate = claims.getExpiration();
        } catch (Exception e) {
            expirationDate = null;
        }
        return expirationDate;
    }

    // Funkcija za preuzimanje JWT tokena iz zahteva
    public String getToken(HttpServletRequest request) {
        String authHeader = getAuthHeaderFromHeader(request);

        /* JWT se prosledjuje kroz header Authorization u formatu:
        Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c */
        if (authHeader != null && authHeader.startsWith("Bearer"))
            return authHeader.substring(7);

        return null;
    }

    public String getAuthHeaderFromHeader(HttpServletRequest request) {return request.getHeader(AUTH_HEADER); }

    private boolean isTokenExpired(String token) {
        Date expirationDate = getExpirationDateFromToken(token);
        return expirationDate.before(new Date(System.currentTimeMillis()));
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getEmailFromToken(token);
        System.out.println("Sifra je " + userDetails.getPassword());
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Long getExpiredIn() {
        return EXPIRES_IN;
    }

}

package com.projekat.UPIB.web.controllers;

import com.projekat.UPIB.models.RefreshToken;
import com.projekat.UPIB.payload.request.TokenRefreshRequest;
import com.projekat.UPIB.payload.response.JwtResponse;
import com.projekat.UPIB.payload.response.TokenRefreshResponse;
import com.projekat.UPIB.services.implementation.RefreshTokenService;
import com.projekat.UPIB.support.exceptions.TokenRefreshException;
import com.projekat.UPIB.web.dto.KorisnikLoginDTO;
import com.projekat.UPIB.models.Korisnik;
import com.projekat.UPIB.security.TokenUtils;
import com.projekat.UPIB.services.ILekarService;
import com.projekat.UPIB.services.IPacijentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/korisnici")
public class KorisnikController {

//    @Autowired
//    private Converter<User, UserDto> toDto;
//
//    @Autowired
//    private Converter<List<User>, List<UserDto>> toDtoList;
//
//    @Autowired
//    private Converter<UserDto, User> toUser;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private IPacijentService pacijentService;

    @Autowired
    private ILekarService lekarService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    //TODO: Dodati za medicinsku sestru. - WLQ


    // Prvi endpoint koji pogadja korisnik kada se loguje.
    // Tada zna samo svoje korisnicko ime i lozinku i to prosledjuje na backend.
    @PostMapping(value =  "/prijava", consumes = "application/json")
    public ResponseEntity<?> loginKorisnik(@RequestBody KorisnikLoginDTO korisnikLoginDTO) {

        System.out.println(korisnikLoginDTO);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        korisnikLoginDTO.getEmailKorisnika(),
                        korisnikLoginDTO.getLozinkaKorisnika()
                )
        );

        // Ubaci korisnika u trenutni context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Korisnik userDetails = (Korisnik) authentication.getPrincipal();

        System.out.println("Ulogovan korisnik je " + userDetails);

        //Kreiraj token za tog korisnika
        String jwt = tokenUtils.generateJwtToken(authentication);

        String roles = userDetails.getAuthoritiesAsString();

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getIdKorisnika());

        return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(), userDetails.getIdKorisnika(), userDetails.getEmailKorisnika(), roles));
    }


    @PreAuthorize("hasRole('ADMINISTRATOR','LEKAR','PACIJENT','MEDICINSKA_SESTRA')")
    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();
        // Ovde ce puci ako je refresh token istekao.
        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getKorisnik)
                .map(korisnik -> {
                    String token = tokenUtils.generateJwtToken(requestRefreshToken);
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!!"));
    }

}

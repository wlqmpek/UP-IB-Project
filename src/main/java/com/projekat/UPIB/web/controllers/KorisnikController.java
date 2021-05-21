package com.projekat.UPIB.web.controllers;

import com.projekat.UPIB.web.dto.KorisnikLoginDTO;
import com.projekat.UPIB.models.Korisnik;
import com.projekat.UPIB.security.TokenUtils;
import com.projekat.UPIB.services.ILekarService;
import com.projekat.UPIB.services.IPacijentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

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

    //TODO: Dodati za medicinsku sestru. - WLQ

    // Prvi endpoint koji pogadja korisnik kada se loguje.
    // Tada zna samo svoje korisnicko ime i lozinku i to prosledjuje na backend.
    @PostMapping(value =  "/prijava", consumes = "application/json")
    public ResponseEntity<String>  login(@RequestBody KorisnikLoginDTO korisnikLoginDTO, HttpServletResponse response) {

        System.out.println("Pogodjen login");
        System.out.println("Email korisnikLoginDto " + korisnikLoginDTO.getEmailKorisnika() + " password " + korisnikLoginDTO.getLozinkaKorisnika());

        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    korisnikLoginDTO.getEmailKorisnika(), korisnikLoginDTO.getLozinkaKorisnika()
            ));
        } catch (BadCredentialsException bce) {
            System.out.println("Exception");
            return ResponseEntity.status(403).build();
        }

        // Ubaci korisnika u trenutni security kontekst
        SecurityContextHolder.getContext().setAuthentication(authentication);



        Korisnik korisnik = (Korisnik) authentication.getPrincipal();
        System.out.println("Korisnikk " + korisnik);

        String jwt = tokenUtils.generateToken(korisnik.getEmailKorisnika(), korisnik.getAuthoritiesAsString());
        Long isticeU = tokenUtils.getExpiredIn();

        return ResponseEntity.ok(jwt);
    }

}

package com.projekat.UPIB.web.controllers;

import com.projekat.UPIB.models.RefreshToken;
import com.projekat.UPIB.payload.request.TokenRefreshRequest;
import com.projekat.UPIB.payload.response.JwtResponse;
import com.projekat.UPIB.payload.response.TokenRefreshResponse;
import com.projekat.UPIB.services.implementation.PacijentLinkService;
import com.projekat.UPIB.services.implementation.RefreshTokenService;
import com.projekat.UPIB.support.exceptions.TokenRefreshException;
import com.projekat.UPIB.web.dto.korisnik.KorisnikInfoDTO;
import com.projekat.UPIB.web.dto.korisnik.KorisnikLoginDTO;
import com.projekat.UPIB.mail.EmailService;
import com.projekat.UPIB.models.Administrator;
import com.projekat.UPIB.models.Korisnik;
import com.projekat.UPIB.models.Lekar;
import com.projekat.UPIB.models.MedicinskaSestra;
import com.projekat.UPIB.models.Pacijent;
import com.projekat.UPIB.models.PacijentLink;
import com.projekat.UPIB.security.TokenUtils;
import com.projekat.UPIB.security.auth.UserDetailsServiceImpl;
import com.projekat.UPIB.services.IAdministratorService;
import com.projekat.UPIB.services.ILekarService;
import com.projekat.UPIB.services.IMedicinskaSestraService;
import com.projekat.UPIB.services.IPacijentService;
import com.projekat.UPIB.web.dto.korisnik.PasswordChangeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;

@CrossOrigin(origins = "https://localhost:3000")
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
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private IPacijentService pacijentService;

    @Autowired
    private ILekarService lekarService;
    
    @Autowired
    private IMedicinskaSestraService medicinskaSestraService;
    
    @Autowired
    private IAdministratorService administratorService;

    @Autowired
    private PacijentLinkService pacijentLinkService;
    
    @Autowired
    private RefreshTokenService refreshTokenService;
    
    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    

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
    
    @PostMapping(value =  "/emailPrijavaZahtev", consumes = "application/json")
    public ResponseEntity<?> emailPrijavaZahtev(@RequestBody KorisnikLoginDTO korisnikLoginDTO) {

        String email = korisnikLoginDTO.getEmailKorisnika();

        Lekar lekar = lekarService.findLekarByEmailKorisnika(email);
        Pacijent pacijent = pacijentService.findPacijentByEmailKorisnika(email);
        MedicinskaSestra medSestra = medicinskaSestraService.findMedicinskaSestraByEmailKorisnika(email);
        Administrator admin = administratorService.findAdministratorByEmailKorisnika(email);
        
        if (lekar == null && pacijent == null && medSestra == null && admin == null) {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        System.out.println("Zahtev za passwordless login korisnika: " + email);
        //Kreiraj token za tog korisnika
        String jwt = tokenUtils.generateTokenFromEmail(email,10);
        System.out.println(jwt);

        
        
        PacijentLink pacijentLinkRep = pacijentLinkService.findPacijentLinkByEmail(email);
        System.out.println("linkREP"+pacijentLinkRep);
        if (pacijentLinkRep != null) {
        	pacijentLinkService.remove(email);
        }
        LocalDateTime datumIsteka = LocalDateTime.now().plusMinutes(10);
        PacijentLink pacijentLink = new PacijentLink();
        pacijentLink.setEmail(email);
        pacijentLink.setDatumIsteka(datumIsteka);
        pacijentLink.setValid(true);
        pacijentLink.setPutanja(" ");
        pacijentLinkService.save(pacijentLink);

        emailService.sendMessage(email, "Email prijava", "https://localhost:3000/email-prijava/"+jwt);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
    @GetMapping(value =  "/emailPrijava/{token}")
    public ResponseEntity<?> emailPrijava(@PathVariable("token") String token) {

    	System.out.println(token);
    	System.out.println("POZVANA EMAIL PRIJAVA");
        String email = tokenUtils.getEmailFromToken(token);
        PacijentLink pacijentLink = pacijentLinkService.findPacijentLinkByEmail(email);
        if (pacijentLink == null || !tokenUtils.validateJwtToken(token)) {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        pacijentLinkService.remove(email);
        
    	UserDetails userDetails = userDetailsService.loadUserByUsername(email);
    	
    	if (userDetails != null) {
	        UsernamePasswordAuthenticationToken authentication =
	                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	
	        // Ubaci korisnika u trenutni context
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	
	        Korisnik user = (Korisnik) userDetailsServiceImpl.loadUserByUsername(email);
	
	        System.out.println("Ulogovan korisnik je " + user.getUsername());
	
	        //Kreiraj token za tog korisnika
	        String jwt = tokenUtils.generateJwtToken(authentication);
	
	        String roles = user.getAuthoritiesAsString();
	
	        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getIdKorisnika());
	
	        return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(), user.getIdKorisnika(), user.getEmailKorisnika(), roles));
    	}
	    else {
	    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }

    }


    @PreAuthorize("hasAnyRole('ADMINISTRATOR','LEKAR','PACIJENT','MEDICINSKA_SESTRA')")
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

    @PreAuthorize("hasAnyRole('LEKAR, ADMINISTRATOR, MEDICINSKA_SESTRA, PACIJENT, KLINICKI_ADMINISTRATOR')")
    @PutMapping("/promena-lozinke")
    public ResponseEntity<Void> changePassword(@RequestBody PasswordChangeDTO passwordChangeDTO, Principal principal){

        Lekar lekar;
        MedicinskaSestra medicinskaSestra;
        Administrator administrator;
        Pacijent pacijent;

        String username = principal.getName();
        String password = passwordEncoder.encode(passwordChangeDTO.getPassword());

        lekar = lekarService.findLekarByEmailKorisnika(username);
        if(lekar != null){
            lekar.setLozinkaKorisnika(password);
            lekarService.save(lekar);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        medicinskaSestra = medicinskaSestraService.findMedicinskaSestraByEmailKorisnika(username);
        if(medicinskaSestra != null) {
            medicinskaSestra.setLozinkaKorisnika(password);
            medicinskaSestraService.save(medicinskaSestra);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        administrator = administratorService.findAdministratorByEmailKorisnika(username);
        if(administrator != null){
            administrator.setLozinkaKorisnika(password);
            administratorService.save(administrator);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        pacijent = pacijentService.findPacijentByEmailKorisnika(username);
        if(pacijent != null){
            pacijent.setLozinkaKorisnika(password);
            pacijentService.save(pacijent);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasAnyRole('LEKAR, ADMINISTRATOR, MEDICINSKA_SESTRA, PACIJENT, KLINICKI_ADMINISTRATOR')")
    @GetMapping("/korisnik-info")
    public ResponseEntity<KorisnikInfoDTO> getInfo(Principal principal){

        Lekar lekar;
        MedicinskaSestra medicinskaSestra;
        Administrator administrator;
        Pacijent pacijent;

        String username = principal.getName();

        lekar = lekarService.findLekarByEmailKorisnika(username);
        if(lekar != null){
            return new ResponseEntity<>(new KorisnikInfoDTO(lekar), HttpStatus.OK);
        }

        medicinskaSestra = medicinskaSestraService.findMedicinskaSestraByEmailKorisnika(username);
        if(medicinskaSestra != null) {
            return new ResponseEntity<>(new KorisnikInfoDTO(medicinskaSestra), HttpStatus.OK);
        }

        administrator = administratorService.findAdministratorByEmailKorisnika(username);
        if(administrator != null){
            return new ResponseEntity<>(new KorisnikInfoDTO(administrator), HttpStatus.OK);
        }

        pacijent = pacijentService.findPacijentByEmailKorisnika(username);
        if(pacijent != null){
            return new ResponseEntity<>(new KorisnikInfoDTO(pacijent), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasAnyRole('LEKAR, ADMINISTRATOR, MEDICINSKA_SESTRA, PACIJENT, KLINICKI_ADMINISTRATOR')")
    @PutMapping("/izmena-podataka")
    public ResponseEntity<Void> changeInfo(@RequestBody KorisnikInfoDTO korisnikInfoDTO, Principal principal){

        Lekar lekar;
        MedicinskaSestra medicinskaSestra;
        Administrator administrator;
        Pacijent pacijent;

        String username = principal.getName();

        lekar = lekarService.findLekarByEmailKorisnika(username);
        if(lekar != null){
            lekar.setImeKorisnika(korisnikInfoDTO.getIme());
            lekar.setPrezimeKorisnika(korisnikInfoDTO.getPrezime());
            lekar.setEmailKorisnika(korisnikInfoDTO.getEmail());
            lekarService.save(lekar);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        medicinskaSestra = medicinskaSestraService.findMedicinskaSestraByEmailKorisnika(username);
        if(medicinskaSestra != null) {
            medicinskaSestra.setImeKorisnika(korisnikInfoDTO.getIme());
            medicinskaSestra.setPrezimeKorisnika(korisnikInfoDTO.getPrezime());
            medicinskaSestra.setEmailKorisnika(korisnikInfoDTO.getEmail());
            medicinskaSestraService.save(medicinskaSestra);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        administrator = administratorService.findAdministratorByEmailKorisnika(username);
        if(administrator != null){
            administrator.setImeKorisnika(korisnikInfoDTO.getIme());
            administrator.setPrezimeKorisnika(korisnikInfoDTO.getPrezime());
            administrator.setEmailKorisnika(korisnikInfoDTO.getEmail());
            administratorService.save(administrator);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        pacijent = pacijentService.findPacijentByEmailKorisnika(username);
        if(pacijent != null){
            pacijent.setImeKorisnika(korisnikInfoDTO.getIme());
            pacijent.setPrezimeKorisnika(korisnikInfoDTO.getPrezime());
            pacijent.setEmailKorisnika(korisnikInfoDTO.getEmail());
            pacijentService.save(pacijent);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

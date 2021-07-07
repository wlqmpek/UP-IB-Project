package com.projekat.UPIB.web.controllers;

import com.projekat.UPIB.services.IZdravstveniKarton;
import com.projekat.UPIB.services.implementation.AuthorityService;
import com.projekat.UPIB.support.converters.PacijentEditDtoToPacijent;
import com.projekat.UPIB.support.converters.PacijentToPacijentFrontDto;
import com.projekat.UPIB.web.dto.pacijent.*;
import com.projekat.UPIB.enums.StatusKorisnika;
import com.projekat.UPIB.models.Pacijent;
import com.projekat.UPIB.models.ZdravstveniKarton;
import com.projekat.UPIB.services.IPacijentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "https://localhost:3000")
@RequestMapping(value = "/Pacijenti")
public class PacijentController {

    private static final String SECRET = "DQ5vLW9QCh";

    @Autowired
    private IPacijentService pacijentService;

    @Autowired
    private IZdravstveniKarton zdravstveniKarton;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityService authorityService;


    @Autowired
    private PacijentEditDtoToPacijent pacijentEditDtoToPacijent;

    @Autowired
    private PacijentToPacijentFrontDto pacijentToPacijentFrontDto;

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping
    public ResponseEntity<List<PacijentFrontDTO>> findAll(){

        List<PacijentFrontDTO> retVal = new ArrayList<>();
        List<Pacijent> pacijenti = pacijentService.findAll();
        for (Pacijent pacijent : pacijenti) {
            PacijentFrontDTO frontDTO = new PacijentFrontDTO(pacijent);
            retVal.add(frontDTO);
        }
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMINISTRATOR','PACIJENT')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<PacijentFrontDTO> findOne(@PathVariable(name = "id") Long id){

        Pacijent pacijent = pacijentService.findOne(id);
        PacijentFrontDTO frontDTO = new PacijentFrontDTO(pacijent);
        if(pacijent == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(frontDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<PacijentRegisterDTO> savePacijent(@RequestBody PacijentRegisterDTO pacijent){

        String hash = passwordEncoder.encode(pacijent.getLozinka());

        Pacijent registered = new Pacijent();
        registered.setStatusKorisnika(StatusKorisnika.NA_CEKANJU);

        registered.setImeKorisnika(pacijent.getIme());
        registered.setPrezimeKorisnika(pacijent.getPrezime());
        registered.setEmailKorisnika(pacijent.getEmail());
        registered.setLozinkaKorisnika(hash);
        registered.setJBZO(pacijent.getJBZO());
        registered.setZdravstveniKarton(new ZdravstveniKarton());
        registered.getZdravstveniKarton().setPacijent(registered);
        registered.setAuthorities(authorityService.findByIdAuthority(pacijent.getAuthorities()));

        registered = pacijentService.save(registered);
        String proba = registered.getLozinkaKorisnika();
        pacijent = new PacijentRegisterDTO(registered);

        return new ResponseEntity<>(pacijent, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PutMapping(consumes = "application/json", value = "/{id}")
    public ResponseEntity<PacijentAdminEditDTO> updatePacijent(@PathVariable(name = "id") Long id,
                                                               @RequestBody PacijentAdminEditDTO pacijent){

        Pacijent pacijentOld = pacijentService.findOne(id);
        if(pacijentOld == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        pacijentOld.setImeKorisnika(pacijent.getIme());
        pacijentOld.setLozinkaKorisnika(passwordEncoder.encode(pacijent.getLozinka()));
        pacijentOld.setPrezimeKorisnika(pacijent.getPrezime());

        pacijentOld = pacijentService.save(pacijentOld);
        pacijent = new PacijentAdminEditDTO(pacijentOld);

        return new ResponseEntity<>(pacijent, HttpStatus.OK);
    }

    //Ova metoda je moja i koristi se da pacijent menja svoje podatke! - WLQ
    @PreAuthorize("hasRole('PACIJENT')")
    @PutMapping(consumes = "application/json", value = "/izmeni/{id}")
    public ResponseEntity<PacijentFrontDTO> izmeniPacijent(@PathVariable(name = "id") Long id,
                                                        @RequestBody PacijentEditDto pacijentNew){
        ResponseEntity responseEntity = null;
        Pacijent pacijentOld = pacijentService.findOne(id);
        Pacijent pacijent = pacijentEditDtoToPacijent.convert(pacijentOld, pacijentNew);

        pacijent = pacijentService.save(pacijentOld);

        responseEntity = (pacijent == null) ? new ResponseEntity(pacijent, HttpStatus.NOT_FOUND) : new ResponseEntity(pacijent, HttpStatus.OK);
        return responseEntity;
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePacijent(@PathVariable(name = "id") Long id){

        Pacijent pacijent = pacijentService.findOne(id);
        if(pacijent == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        pacijentService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
    @PostMapping(value = "/login", consumes = "application/json")
    public ResponseEntity<PacijentFrontDTO> loginPacijent(@RequestBody PacijentLoginDTO pacijentLoginDTO) {

        System.out.println("Pogodjeno!");
        System.out.println("Email " + pacijentLoginDTO.getEmailKorisnika());
        System.out.println("Lozinka " +pacijentLoginDTO.getLozinkaKorisnika());

        Pacijent pacijent = pacijentService.findPacijentByEmailKorisnika(pacijentLoginDTO.getEmailKorisnika());

        if(pacijent != null && pacijent.getLozinkaKorisnika().equals(pacijentLoginDTO.getLozinkaKorisnika())) {

            PacijentFrontDTO pacijentFrontDTO = new PacijentFrontDTO(pacijent);

            return new ResponseEntity<>(pacijentFrontDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    
    
}

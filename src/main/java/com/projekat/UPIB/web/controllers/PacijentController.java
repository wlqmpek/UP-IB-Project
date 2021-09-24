package com.projekat.UPIB.web.controllers;

import com.projekat.UPIB.mail.IEmailService;
import com.projekat.UPIB.security.EnkripcijaDekripcijaUtils;
import com.projekat.UPIB.services.IZdravstveniKarton;
import com.projekat.UPIB.services.implementation.AuthorityService;
import com.projekat.UPIB.support.converters.pacijent.PacijentEditDtoToPacijent;
import com.projekat.UPIB.support.converters.pacijent.PacijentToPacijentFrontDto;
import com.projekat.UPIB.support.xml_parser.ZdravstveniKartonRepo;
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
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "https://localhost:3000")
@RequestMapping(value = "/Pacijenti")
public class PacijentController {

    private static final String SECRET = "DQ5vLW9QCh";

    @Autowired
    private IPacijentService pacijentService;

//    @Autowired
//    private IZdravstveniKarton zdravstveniKarton;

    @Autowired
    private ZdravstveniKartonRepo kartonRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private EnkripcijaDekripcijaUtils enkripcijaDekripcijaUtils;

    @Autowired
    private PacijentEditDtoToPacijent pacijentEditDtoToPacijent;

    @Autowired
    private PacijentToPacijentFrontDto pacijentToPacijentFrontDto;

    @PreAuthorize("hasAnyRole('KLINICKI_ADMINISTRATOR', 'LEKAR','MEDICINSKA_SESTRA')")
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

    @PreAuthorize("hasAnyRole('KLINICKI_ADMINISTRATOR','PACIJENT','MEDICINSKA_SESTRA')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<PacijentFrontDTO> findOne(@PathVariable(name = "id") Long id){

        Pacijent pacijent = pacijentService.findOne(id);
        PacijentFrontDTO frontDTO = pacijentToPacijentFrontDto.convert(pacijent);
        if(pacijent == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(frontDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ANONYMOUS')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<PacijentRegisterDTO> savePacijent(@RequestBody PacijentRegisterDTO pacijent) throws ParserConfigurationException, IOException, SAXException {

        String hash = passwordEncoder.encode(pacijent.getLozinka());

        Pacijent registered = new Pacijent();
        registered.setStatusKorisnika(StatusKorisnika.NA_CEKANJU);

        registered.setImeKorisnika(pacijent.getIme());
        registered.setPrezimeKorisnika(pacijent.getPrezime());
        registered.setEmailKorisnika(pacijent.getEmail());
        registered.setLozinkaKorisnika(hash);
        registered.setJBZO(pacijent.getJBZO());
        ZdravstveniKarton karton = new ZdravstveniKarton();
        karton.setPacijent(registered);
//        kartonRepo.saveZK(karton);
        registered.setZdravstveniKarton(new ZdravstveniKarton());
        registered.getZdravstveniKarton().setPacijent(registered);

        registered.setAuthorities(authorityService.findByIdAuthority(pacijent.getAuthorities()));

        registered = pacijentService.save(registered);
        pacijent = new PacijentRegisterDTO(registered);


        return new ResponseEntity<>(pacijent, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('KLINICKI_ADMINISTRATOR')")
    @PutMapping(consumes = "application/json", value = "/{id}")
    public ResponseEntity<PacijentFrontDTO> updatePacijent(@PathVariable(name = "id") Long id,
                                                               @RequestBody PacijentFrontDTO pacijent){

        Pacijent pacijentOld = pacijentService.findOne(id);
        if(pacijentOld == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        pacijentOld.setImeKorisnika(pacijent.getIme());
        //pacijentOld.setLozinkaKorisnika(pacijent.getLozinka());
        pacijentOld.setPrezimeKorisnika(pacijent.getPrezime());
        pacijentOld.setStatusKorisnika(pacijent.getStatusKorisnika());

        pacijentOld = pacijentService.update(pacijentOld);
        pacijent = new PacijentFrontDTO(pacijentOld);

        return new ResponseEntity<>(pacijent, HttpStatus.OK);
    }

    //Ova metoda je moja i koristi se da pacijent menja svoje podatke! - WLQ
    @PreAuthorize("hasRole('PACIJENT')")
    @PutMapping(consumes = "application/json", value = "/izmeni")
    public ResponseEntity<PacijentFrontDTO> izmeniPacijent(@RequestBody PacijentEditDto pacijentNew, Principal p){
        ResponseEntity responseEntity = null;
        Pacijent pacijentOld = pacijentService.findPacijentByEmailKorisnika(p.getName());
        Pacijent pacijent = pacijentEditDtoToPacijent.convert(pacijentOld, pacijentNew);

        pacijent = pacijentService.update(pacijentOld);
        responseEntity = (pacijent == null) ? new ResponseEntity(pacijent, HttpStatus.NOT_FOUND) : new ResponseEntity(pacijent, HttpStatus.OK);
        return responseEntity;
    }

    @PreAuthorize("hasRole('KLINICKI_ADMINISTRATOR')")
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

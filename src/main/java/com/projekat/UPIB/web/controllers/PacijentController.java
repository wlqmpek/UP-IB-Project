package com.projekat.UPIB.web.controllers;

import com.projekat.UPIB.services.IZdravstveniKarton;
import com.projekat.UPIB.web.dto.pacijent.PacijentFrontDTO;
import com.projekat.UPIB.web.dto.pacijent.PacijentLoginDTO;
import com.projekat.UPIB.web.dto.pacijent.PacijentRegisterDTO;
import com.projekat.UPIB.enums.StatusKorisnika;
import com.projekat.UPIB.models.Pacijent;
import com.projekat.UPIB.models.ZdravstveniKarton;
import com.projekat.UPIB.services.IPacijentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/Pacijenti")
public class PacijentController {

    @Autowired
    private IPacijentService pacijentService;

    @Autowired
    private IZdravstveniKarton zdravstveniKarton;

    @GetMapping
    public ResponseEntity<List<PacijentRegisterDTO>> findAll(){

        List<PacijentRegisterDTO> retVal = new ArrayList<>();
        List<Pacijent> pacijenti = pacijentService.findAll();
        for (Pacijent pacijent : pacijenti) {
            PacijentRegisterDTO registerDTO = new PacijentRegisterDTO(pacijent);
            retVal.add(registerDTO);
        }
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PacijentRegisterDTO> findOne(@PathVariable(name = "id") Long id){

        Pacijent pacijent = pacijentService.findOne(id);
        PacijentRegisterDTO registerDTO = new PacijentRegisterDTO(pacijent);
        if(pacijent == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(registerDTO, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<PacijentRegisterDTO> savePacijent(@RequestBody PacijentRegisterDTO pacijent){

        Pacijent registered = new Pacijent();
        registered.setStatusKorisnika(StatusKorisnika.NA_CEKANJU);

        registered.setImeKorisnika(pacijent.getIme());
        registered.setPrezimeKorisnika(pacijent.getPrezime());
        registered.setEmailKorisnika(pacijent.getEmail());
        registered.setLozinkaKorisnika(pacijent.getLozinka());
        registered.setJBZO(pacijent.getJBZO());
        registered.setZdravstveniKarton(new ZdravstveniKarton());
        registered.getZdravstveniKarton().setPacijent(registered);

        registered = pacijentService.save(registered);
        pacijent = new PacijentRegisterDTO(registered);

        return new ResponseEntity<>(pacijent, HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json", value = "/{id}")
    public ResponseEntity<PacijentRegisterDTO> updatePacijent(@PathVariable(name = "id") Long id,
                                                              @RequestBody PacijentRegisterDTO pacijent){

        Pacijent pacijentOld = pacijentService.findOne(id);
        if(pacijentOld == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        pacijentOld.setImeKorisnika(pacijent.getIme());
        pacijentOld.setEmailKorisnika(pacijent.getEmail());
        pacijentOld.setLozinkaKorisnika(pacijent.getLozinka());
        pacijentOld.setPrezimeKorisnika(pacijent.getPrezime());

        pacijentOld = pacijentService.save(pacijentOld);
        pacijent = new PacijentRegisterDTO(pacijentOld);

        return new ResponseEntity<>(pacijent, HttpStatus.OK);
    }

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

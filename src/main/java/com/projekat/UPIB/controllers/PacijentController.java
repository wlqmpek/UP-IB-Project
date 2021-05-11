package com.projekat.UPIB.controllers;

import com.projekat.UPIB.dto.PacijentFrontDTO;
import com.projekat.UPIB.dto.PacijentLoginDTO;
import com.projekat.UPIB.dto.PacijentRegisterDTO;
import com.projekat.UPIB.dto.PacijentRegisterDTO;
import com.projekat.UPIB.enums.StatusKorisnika;
import com.projekat.UPIB.models.Pacijent;
import com.projekat.UPIB.models.ZdravstveniKarton;
import com.projekat.UPIB.services.IPacijentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/Pacijenti")
public class PacijentController {

    @Autowired
    private IPacijentService pacijentService;

    @GetMapping
    public ResponseEntity<List<Pacijent>> findAll(){

        List<Pacijent> pacijenti = pacijentService.findAll();
        return new ResponseEntity<List<Pacijent>>(pacijenti, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pacijent> findOne(@PathVariable(name = "id") Long id){

        Pacijent pacijent = pacijentService.findOne(id);
        if(pacijent == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(pacijent, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Pacijent> savePacijent(@RequestBody Pacijent pacijent){

        pacijent.setZdravstveniKarton(new ZdravstveniKarton());
        pacijent.setStatusKorisnika(StatusKorisnika.NA_CEKANJU);
        pacijent.getZdravstveniKarton().setPacijent(pacijent);
        pacijentService.save(pacijent);
        return new ResponseEntity<>(pacijent, HttpStatus.CREATED);
    }

    //TODO: Umesto da radimo convertovanje iz DTO U Entity i obrnuto
    //u kontroleru uraditi da se isto radi u konverteru primer se nalazi
    //u IB Vezva 7 oko 32min
    @PutMapping(consumes = "application/json", value = "/{id}")
    public ResponseEntity<Pacijent> updatePacijent(@PathVariable(name = "id") Long id, @RequestBody Pacijent pacijent){

        Pacijent pacijentOld = pacijentService.findOne(id);
        if(pacijentOld == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        pacijentOld.setImeKorisnika(pacijent.getImeKorisnika());
        pacijentOld.setEmailKorisnika(pacijent.getEmailKorisnika());
        pacijentOld.setLozinkaKorisnika(pacijent.getLozinkaKorisnika());
        pacijentOld.setPrezimeKorisnika(pacijent.getPrezimeKorisnika());
        pacijentOld.setStatusKorisnika(pacijent.getStatusKorisnika());
        pacijentOld.setZdravstveniKarton(pacijent.getZdravstveniKarton());

        pacijentOld = pacijentService.save(pacijentOld);

        return new ResponseEntity<>(pacijentOld, HttpStatus.OK);
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

    //Komentar
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

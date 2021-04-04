package com.projekat.UPIB.controllers;

import com.projekat.UPIB.models.Pacijent;
import com.projekat.UPIB.services.IPacijentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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

        pacijentService.save(pacijent);
        return new ResponseEntity<>(pacijent, HttpStatus.CREATED);
    }

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
}

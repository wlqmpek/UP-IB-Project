package com.projekat.UPIB.controllers;

import com.projekat.UPIB.models.Lekar;
import com.projekat.UPIB.services.ILekarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/Lekari")
public class LekarController {

    @Autowired
    private ILekarService lekarService;

    @GetMapping
    public ResponseEntity<List<Lekar>> findAll(){

        List<Lekar> lekari = lekarService.findAll();
        return new ResponseEntity<>(lekari, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Lekar> findOne(@PathVariable(name = "id") Long id){

        Lekar lekar = lekarService.findOne(id);
        if(lekar == null){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return  new ResponseEntity<>(lekar, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Lekar> saveLekar(@RequestBody Lekar lekar){

        lekarService.save(lekar);
        return new ResponseEntity<>(lekar, HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json", value = "/{id}")
    public ResponseEntity<Lekar> updateLekar(@PathVariable(name = "id") Long id, @RequestBody Lekar lekar){

        Lekar lekarOld = lekarService.findOne(id);
        if(lekarOld == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        lekarOld.setEmailKorisnika(lekar.getEmailKorisnika());
        lekarOld.setImeKorisnika(lekar.getImeKorisnika());
        lekarOld.setLozinkaKorisnika(lekar.getLozinkaKorisnika());
        lekarOld.setPrezimeKorisnika(lekar.getPrezimeKorisnika());
        lekarOld.setPregledi(lekar.getPregledi());
        lekarOld.setKlinika(lekar.getKlinika());

        lekarOld = lekarService.save(lekarOld);

        return new ResponseEntity<>(lekarOld, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteLekar(@PathVariable(name = "id") Long id){

        Lekar lekar = lekarService.findOne(id);
        if(lekar == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        lekarService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

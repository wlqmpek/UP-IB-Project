package com.projekat.UPIB.controllers;

import java.util.HashSet;
import java.util.List;

import com.projekat.UPIB.dto.KlinikaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projekat.UPIB.models.Klinika;
import com.projekat.UPIB.services.IKlinikaService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/Klinike")
public class KlinikaController {
	
	@Autowired
    private IKlinikaService klinikaService;

    @GetMapping
    public ResponseEntity<List<Klinika>> findAll(){

        List<Klinika> klinike = klinikaService.findAll();
        return new ResponseEntity<List<Klinika>>(klinike, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Klinika> findOne(@PathVariable(name = "id") Long id){

    	Klinika klinika = klinikaService.findOne(id);
        if(klinika == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(klinika, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Klinika> saveKlinika(@RequestBody KlinikaDTO klinikaDTO){

        Klinika klinika = new Klinika();
        klinika.setNaziv(klinikaDTO.getNaziv());
        klinika.setOpis(klinikaDTO.getOpis());
        klinika.setAdresa(klinikaDTO.getAdresa());
        klinika.setSlobodniTermini(new HashSet<>());
        klinika.setAdministratori(new HashSet<>());
    	klinikaService.save(klinika);
        return new ResponseEntity<>(klinika, HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json", value = "/{id}")
    public ResponseEntity<Klinika> updateKlinika(@PathVariable(name = "id") Long id, @RequestBody Klinika klinika){

    	Klinika klinikaOld = klinikaService.findOne(id);
        if(klinikaOld == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        klinikaOld.setNaziv(klinika.getNaziv());
        klinikaOld.setOpis(klinika.getOpis());
        klinikaOld.setAdresa(klinika.getAdresa());
        klinikaOld.setSlobodniTermini(klinika.getSlobodniTermini());
        klinikaOld.setAdministratori(klinika.getAdministratori());

        klinikaOld = klinikaService.save(klinikaOld);

        return new ResponseEntity<>(klinikaOld, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteKlinika(@PathVariable(name = "id") Long id){

    	Klinika klinika = klinikaService.findOne(id);
        if(klinika == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        klinikaService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
}

package com.projekat.UPIB.controllers;

import com.projekat.UPIB.dto.LekarDTO;
import com.projekat.UPIB.dto.PregledDTO;
import com.projekat.UPIB.models.Klinika;
import com.projekat.UPIB.models.Lekar;
import com.projekat.UPIB.models.Pregled;
import com.projekat.UPIB.services.IKlinikaService;
import com.projekat.UPIB.services.ILekarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/Lekari")
public class LekarController {

    @Autowired
    private ILekarService lekarService;
    @Autowired
    private IKlinikaService klinikaService;

    @GetMapping
    public ResponseEntity<List<LekarDTO>> findAll(){

        List<LekarDTO> retVal = new ArrayList<>();
        List<Lekar> lekari = lekarService.findAll();
        for(Lekar lekar : lekari){
            LekarDTO lekarDTO = new LekarDTO(lekar);
            retVal.add(lekarDTO);
        }
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<LekarDTO> findOne(@PathVariable(name = "id") Long id){

        Lekar lekar = lekarService.findOne(id);
        if(lekar == null){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LekarDTO lekarDTO = new LekarDTO(lekar);

        return  new ResponseEntity<>(lekarDTO, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<LekarDTO> saveLekar(@RequestBody LekarDTO lekarDTO){

        Lekar lekar = new Lekar(lekarDTO);
        Klinika klinika = klinikaService.findOne(lekarDTO.getIdKlinike());
        lekar.setKlinika(klinika);
        lekar.setPregledi(new HashSet<>());

        lekarService.save(lekar);
        return new ResponseEntity<>(lekarDTO, HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json", value = "/{id}")
    public ResponseEntity<LekarDTO> updateLekar(@PathVariable(name = "id") Long id, @RequestBody LekarDTO lekar){

        Lekar lekarOld = lekarService.findOne(id);
        if(lekarOld == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        lekarOld.setEmailKorisnika(lekar.getEmailKorisnika());
        lekarOld.setImeKorisnika(lekar.getImeKorisnika());
        lekarOld.setLozinkaKorisnika(lekar.getLozinkaKorisnika());
        lekarOld.setPrezimeKorisnika(lekar.getPrezimeKorisnika());

        Klinika klinika = klinikaService.findOne(lekar.getIdKlinike());
        lekarOld.setKlinika(klinika);

        lekarOld = lekarService.save(lekarOld);

        return new ResponseEntity<>(lekar, HttpStatus.OK);
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

    @PutMapping(consumes = "application/json", value = "/pregled/{id}")
    public ResponseEntity<Void> addPregled(@PathVariable(name = "id") long id, @RequestBody Pregled pregledDTO){
        Lekar lekar = lekarService.findOne(id);

        lekar.getPregledi().add(pregledDTO);
        LekarDTO lekarDTO = new LekarDTO(lekar);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

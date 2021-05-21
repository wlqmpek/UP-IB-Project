package com.projekat.UPIB.web.controllers;

import com.projekat.UPIB.enums.StatusKorisnika;
import com.projekat.UPIB.models.Pacijent;
import com.projekat.UPIB.services.implementation.PacijentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/Zahtevi")
public class ZahteviZaRegistracijuController {

    @Autowired
    private PacijentService pacijentService;

    @GetMapping
    public ResponseEntity<List<Pacijent>> getPacijents(){
        List<Pacijent> pacijenti = pacijentService.findAll();
        List<Pacijent> retVal = new ArrayList<>();

        for (Pacijent pacijent : pacijenti) {
            if(pacijent.getStatusKorisnika().equals(StatusKorisnika.NA_CEKANJU)){
                retVal.add(pacijent);
            }
        }

        return  new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PutMapping(consumes = "application/json", value = "/{id}")
    public ResponseEntity<Pacijent> getPacijent(@PathVariable("id") long id, @RequestBody Pacijent pacijent){

        Pacijent retVal = pacijentService.findOne(id);
        if(retVal == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        retVal.setStatusKorisnika(pacijent.getStatusKorisnika());
        retVal = pacijentService.save(retVal);

        return new ResponseEntity<>(retVal, HttpStatus.ACCEPTED);
    }
}

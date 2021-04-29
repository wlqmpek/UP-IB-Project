package com.projekat.UPIB.controllers;

import com.projekat.UPIB.dto.PacijentRegisterDTO;
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
    public ResponseEntity<List<PacijentRegisterDTO>> getPacijents(){
        List<Pacijent> pacijenti = pacijentService.findAll();
        List<PacijentRegisterDTO> retVal = new ArrayList<>();

        for (Pacijent pacijent : pacijenti) {
            if(pacijent.getStatusKorisnika().equals(StatusKorisnika.NA_CEKANJU)){
                PacijentRegisterDTO registerDTO = new PacijentRegisterDTO(pacijent);
                retVal.add(registerDTO);
            }
        }

        return  new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PutMapping(consumes = "application/json", value = "/{id}")
    public ResponseEntity<PacijentRegisterDTO> getPacijent(@PathVariable("id") long id,
                                                           @RequestBody PacijentRegisterDTO pacijent){

        Pacijent retVal = pacijentService.findOne(id);
        if(retVal == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        retVal.setStatusKorisnika(pacijent.getStatusKorisnika());
        retVal = pacijentService.save(retVal);

        PacijentRegisterDTO registerDTO = new PacijentRegisterDTO(retVal);

        return new ResponseEntity<>(registerDTO, HttpStatus.ACCEPTED);
    }
}

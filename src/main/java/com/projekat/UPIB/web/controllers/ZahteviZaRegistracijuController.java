package com.projekat.UPIB.web.controllers;

import com.projekat.UPIB.mail.EmailService;
import com.projekat.UPIB.web.dto.pacijent.PacijentFrontDTO;
import com.projekat.UPIB.web.dto.pacijent.PacijentRegisterDTO;
import com.projekat.UPIB.enums.StatusKorisnika;
import com.projekat.UPIB.models.Pacijent;
import com.projekat.UPIB.services.implementation.PacijentService;
import com.projekat.UPIB.web.dto.pacijent.PacijentStatusDTO;
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
    private EmailService emailService;

    @Autowired
    private PacijentService pacijentService;

    @GetMapping
    public ResponseEntity<List<PacijentFrontDTO>> getPacijents(){
        List<Pacijent> pacijenti = pacijentService.findAll();
        List<PacijentFrontDTO> retVal = new ArrayList<>();

        for (Pacijent pacijent : pacijenti) {
            if(pacijent.getStatusKorisnika().equals(StatusKorisnika.NA_CEKANJU)){
                PacijentFrontDTO frontDTO = new PacijentFrontDTO(pacijent);
                retVal.add(frontDTO);
            }
        }

        return  new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PutMapping(consumes = "application/json", value = "/{id}")
    public ResponseEntity<PacijentStatusDTO> getPacijent(@PathVariable("id") long id,
                                                           @RequestBody PacijentStatusDTO pacijent){

        Pacijent retVal = pacijentService.findOne(id);
        if(retVal == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        retVal.setStatusKorisnika(pacijent.getStatusKorisnika());
        retVal = pacijentService.save(retVal);

        PacijentStatusDTO pacijentDTO = new PacijentStatusDTO(retVal);
        if(retVal.getStatusKorisnika() == StatusKorisnika.PRIHVACEN){
            emailService.sendMessage(retVal.getEmailKorisnika(), "Registracija na KC Hipokrat",
                    "Vasa registracija je prihvacena.");
        }
        if(retVal.getStatusKorisnika() == StatusKorisnika.ODBIJEN){
            emailService.sendMessage(retVal.getEmailKorisnika(), "Registracija na KC Hipokrat",
                    "Vasa registracija je odbijena.");
        }

        return new ResponseEntity<>(pacijentDTO, HttpStatus.ACCEPTED);
    }

    @GetMapping("/prihvati/{path}")
    public void succesRegistration(@PathVariable("path") String path){
        //TODO provera da li je link validan ako jeste posalji ga na login page
    }

    private void createPath(Pacijent pacijent){
        //TODO kreiranje linka za registraciju pacijenta


    }
}

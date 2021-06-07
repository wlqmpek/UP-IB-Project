package com.projekat.UPIB.web.controllers;

import com.projekat.UPIB.models.Klinika;
import com.projekat.UPIB.web.dto.MedicinskaSestraDTO;
import com.projekat.UPIB.models.MedicinskaSestra;
import com.projekat.UPIB.services.IKlinikaService;
import com.projekat.UPIB.services.IMedicinskaSestraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/MedicinskeSestre")
public class MSestraController {

    @Autowired
    private IMedicinskaSestraService sestraService;

    @Autowired
    private IKlinikaService klinikaService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<List<MedicinskaSestraDTO>> getAll(){

        List<MedicinskaSestra> medicinskeSestre = sestraService.findAll();
        List<MedicinskaSestraDTO> retVal = new ArrayList<>();

        for (MedicinskaSestra sestra : medicinskeSestre){
            retVal.add(new MedicinskaSestraDTO(sestra));
        }

        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicinskaSestraDTO> getOne(@PathVariable("id") Long id){

        MedicinskaSestra medicinskaSestra = sestraService.findOne(id);
        if(medicinskaSestra == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new MedicinskaSestraDTO(medicinskaSestra), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<MedicinskaSestraDTO> createMSestra(@RequestBody MedicinskaSestraDTO medicinskaSestraDTO){

        MedicinskaSestra medicinskaSestra = new MedicinskaSestra();
        Klinika klinika = klinikaService.findOne(medicinskaSestraDTO.getIdKlinike());
        if (klinika == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        medicinskaSestra.setImeKorisnika(medicinskaSestraDTO.getImeKorisnika());
        medicinskaSestra.setPrezimeKorisnika(medicinskaSestraDTO.getPrezimeKorisnika());
        medicinskaSestra.setEmailKorisnika(medicinskaSestraDTO.getEmailKorisnika());
        medicinskaSestra.setLozinkaKorisnika(passwordEncoder.encode(medicinskaSestraDTO.getLozinkaKorisnika()));
        medicinskaSestra.setKlinika(klinika);
        //medicinskaSestra.setPregledi(new HashSet<>());

        medicinskaSestra = sestraService.save(medicinskaSestra);
        medicinskaSestraDTO = new MedicinskaSestraDTO(medicinskaSestra);
        return new ResponseEntity<>(medicinskaSestraDTO, HttpStatus.OK);
    }

    @PutMapping(consumes = "application/json", value = "/{id}")
    public ResponseEntity<MedicinskaSestraDTO> editMSestra(@PathVariable("id") Long id,
                                                           @RequestBody MedicinskaSestraDTO medicinskaSestraDTO){

        MedicinskaSestra medicinskaSestra = sestraService.findOne(id);
        if(medicinskaSestra == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        medicinskaSestra.setImeKorisnika(medicinskaSestraDTO.getImeKorisnika());
        medicinskaSestra.setPrezimeKorisnika(medicinskaSestraDTO.getPrezimeKorisnika());
        medicinskaSestra.setEmailKorisnika(medicinskaSestraDTO.getEmailKorisnika());
        medicinskaSestra.setLozinkaKorisnika(passwordEncoder.encode(medicinskaSestraDTO.getLozinkaKorisnika()));
        medicinskaSestra.setKlinika(klinikaService.findOne(medicinskaSestraDTO.getIdKlinike()));

        medicinskaSestra = sestraService.save(medicinskaSestra);
        medicinskaSestraDTO = new MedicinskaSestraDTO(medicinskaSestra);
        return new ResponseEntity<>(medicinskaSestraDTO, HttpStatus.OK);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteMSestra(@PathVariable("id") Long id){

        if(sestraService.findOne(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        sestraService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

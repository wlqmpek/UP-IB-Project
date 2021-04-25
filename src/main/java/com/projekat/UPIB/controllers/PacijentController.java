package com.projekat.UPIB.controllers;

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
@CrossOrigin
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

    //Ovde menjati kad stignemo do toga da lozinku ne cuvamo kao plain-text vec kao hashovanu vrednost.
    @PostMapping(value = "/login")
    public ResponseEntity<PacijentRegisterDTO> loginPacijent(@RequestParam(name = "email_korisnika") String emailKorisnika,
                                                             @RequestParam(name= "lozinka_korisnika") String lozinkaKorisnika) {

        System.out.println("Pogodjeno!");
        System.out.println("Email " + emailKorisnika);
        System.out.println("Lozinka " +lozinkaKorisnika);

        Pacijent pacijent = pacijentService.findPacijentByEmailKorisnika(emailKorisnika);

        //Ako se lozinka ne podudara referenci nadjenog pacijenta dodeljujemo null.
        //TODO: Ovde promeniti kada stignemo do hashovanja lozinke!
        if(!pacijent.getLozinkaKorisnika().equals(lozinkaKorisnika))
            pacijent = null;

        if(pacijent != null) {

            PacijentRegisterDTO pacijentDTO = new PacijentRegisterDTO();

            return new ResponseEntity<>(pacijentDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }
}

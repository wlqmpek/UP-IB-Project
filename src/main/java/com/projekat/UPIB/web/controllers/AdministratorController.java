package com.projekat.UPIB.web.controllers;

import com.projekat.UPIB.models.Administrator;
import com.projekat.UPIB.services.IAdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://localhost:3000")
@RequestMapping(value = "/Administratori")
public class AdministratorController {

    @Autowired
    private IAdministratorService administratorService;

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping
    public ResponseEntity<List<Administrator>> allAdministrators(){

        List<Administrator> administrators =  administratorService.findAll();
        return new ResponseEntity<List<Administrator>>(administrators,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Administrator> findAdministrator(@PathVariable(name = "id") Long id){

        Administrator administrator = administratorService.findOne(id);
        return new ResponseEntity<Administrator>(administrator, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Administrator> saveAdministrator(@RequestBody Administrator administrator){
        administratorService.save(administrator);
        return new ResponseEntity<Administrator>(administrator, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMINISTRATOR','KLINICKI_ADMINISTRATOR')")
    @PutMapping(value = "/{id}", consumes = "application/json")
    public  ResponseEntity<Administrator> updateAdministrator(@PathVariable(name = "id") Long id,@RequestBody Administrator administrator){

        Administrator admin = administratorService.findOne(id);
        if(admin == null){
            return new ResponseEntity<Administrator>(HttpStatus.BAD_REQUEST);
        }

        admin.setImeKorisnika(administrator.getImeKorisnika());
        admin.setEmailKorisnika(administrator.getEmailKorisnika());
        admin.setLozinkaKorisnika(administrator.getLozinkaKorisnika());
        admin.setPrezimeKorisnika(administrator.getPrezimeKorisnika());

        admin = administratorService.save(admin);

        return new ResponseEntity<Administrator>(admin, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteAdministrator(@PathVariable(name = "id") Long id){

        Administrator administrator = administratorService.findOne(id);
        if(administrator == null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        administratorService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}

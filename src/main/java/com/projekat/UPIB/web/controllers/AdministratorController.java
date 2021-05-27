package com.projekat.UPIB.web.controllers;

import com.projekat.UPIB.web.dto.AdministratorBackendDTO;
import com.projekat.UPIB.web.dto.AdministratorFrontendDTO;
import com.projekat.UPIB.enums.VrstaAdministratora;
import com.projekat.UPIB.models.Administrator;
import com.projekat.UPIB.models.Klinika;
import com.projekat.UPIB.services.IAdministratorService;
import com.projekat.UPIB.services.IKlinikaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/Admini")
public class AdministratorController {

    @Autowired
    private IAdministratorService administratorService;
    
    @Autowired
    private IKlinikaService klinikaService;

    @GetMapping
    public ResponseEntity<List<AdministratorFrontendDTO>> allAdministrators(){

        List<Administrator> administrators =  administratorService.findAll();
        List<AdministratorFrontendDTO> adminiFrontendDTO = new ArrayList<>();
        
        for (Administrator admin: administrators) {
        	AdministratorFrontendDTO adminFrontendDTO = new AdministratorFrontendDTO(admin);
        	adminiFrontendDTO.add(adminFrontendDTO);
        }
        return new ResponseEntity<List<AdministratorFrontendDTO>>(adminiFrontendDTO,HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AdministratorFrontendDTO> findAdministrator(@PathVariable(name = "id") Long id){

        Administrator admin = administratorService.findOne(id);
        if(admin == null){
            return new ResponseEntity<AdministratorFrontendDTO>(HttpStatus.BAD_REQUEST);
        }
        AdministratorFrontendDTO adminFrontDTO = new AdministratorFrontendDTO(admin);
        
        return new ResponseEntity<AdministratorFrontendDTO>(adminFrontDTO, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Administrator> saveAdministrator(@RequestBody AdministratorBackendDTO adminInfo){
        
    	Administrator admin = new Administrator();
    	
    	admin.setImeKorisnika(adminInfo.getImeKorisnika());
    	admin.setPrezimeKorisnika(adminInfo.getPrezimeKorisnika());
    	admin.setEmailKorisnika(adminInfo.getEmailKorisnika());
    	admin.setLozinkaKorisnika(adminInfo.getLozinkaKorisnika());
    	if (adminInfo.getVrstaAdministratora() == VrstaAdministratora.KLINICKOG_CENTRA) {
    		admin.setKlinika(null);
    	}
    	else if (adminInfo.getIdKlinike() != null){
    		admin.setKlinika(klinikaService.findOne(adminInfo.getIdKlinike()));
    	}
    	else {
    		return new ResponseEntity<Administrator>(HttpStatus.BAD_REQUEST);
    	}
    	admin.setVrstaAdministratora(adminInfo.getVrstaAdministratora());
    	
    	administratorService.save(admin);
        return new ResponseEntity<Administrator>(admin, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public  ResponseEntity<Administrator> updateAdministrator(@PathVariable(name = "id") Long id,@RequestBody AdministratorBackendDTO adminInfo){
        Administrator admin = administratorService.findOne(id);
        if(admin == null){
            return new ResponseEntity<Administrator>(HttpStatus.BAD_REQUEST);
        }

        admin.setImeKorisnika(adminInfo.getImeKorisnika());
        admin.setEmailKorisnika(adminInfo.getEmailKorisnika());
        if (!adminInfo.getLozinkaKorisnika().equals("")) {
        	admin.setLozinkaKorisnika(adminInfo.getLozinkaKorisnika());
        }
        admin.setPrezimeKorisnika(adminInfo.getPrezimeKorisnika());
        
        if (adminInfo.getVrstaAdministratora() == VrstaAdministratora.KLINICKOG_CENTRA) {
    		admin.setKlinika(null);
    	}
    	else if (adminInfo.getIdKlinike() != null){
    		admin.setKlinika(klinikaService.findOne(adminInfo.getIdKlinike()));
    	}
    	else {
    		return new ResponseEntity<Administrator>(HttpStatus.BAD_REQUEST);
    	}

        admin = administratorService.save(admin);
        

        return new ResponseEntity<Administrator>(admin, HttpStatus.OK);
    }

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

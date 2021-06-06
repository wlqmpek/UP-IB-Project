package com.projekat.UPIB.web.controllers;

import com.projekat.UPIB.web.dto.LekarBackendDTO;
import com.projekat.UPIB.web.dto.LekarFrontendDTO;
import com.projekat.UPIB.models.Klinika;
import com.projekat.UPIB.models.Lekar;
import com.projekat.UPIB.services.IKlinikaService;
import com.projekat.UPIB.services.ILekarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/Lekari")
public class LekarController {

    @Autowired
    private ILekarService lekarService;
    
    @Autowired
    private IKlinikaService klinikaService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping
    public ResponseEntity<List<LekarFrontendDTO>> findAll(){

        List<Lekar> lekari = lekarService.findAll();
        List<LekarFrontendDTO> lekariFrontendDTO = new ArrayList<>();
        
        for (Lekar lekar : lekari) {
        	LekarFrontendDTO lekarFrontendDTO = new LekarFrontendDTO(lekar);
        	lekariFrontendDTO.add(lekarFrontendDTO);
        }
        return new ResponseEntity<>(lekariFrontendDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMINISTRATOR','LEKAR')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<LekarFrontendDTO> findOne(@PathVariable(name = "id") Long id){

        Lekar lekar = lekarService.findOne(id);
        if(lekar == null){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        LekarFrontendDTO lekarFrontendDTO = new LekarFrontendDTO(lekar);

        return  new ResponseEntity<>(lekarFrontendDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Lekar> saveLekar(@RequestBody LekarBackendDTO lekarInfo){

    	Lekar lekar = new Lekar();
    	lekar.setImeKorisnika(lekarInfo.getImeKorisnika());
    	lekar.setPrezimeKorisnika(lekarInfo.getPrezimeKorisnika());
    	lekar.setLozinkaKorisnika(passwordEncoder.encode(lekarInfo.getLozinkaKorisnika()));
    	lekar.setEmailKorisnika(lekarInfo.getEmailKorisnika());
    	
    	// bad request ukoliko id klinike nije prosledjen ili id nije ispravan
    	if (lekarInfo.getIdKlinike() != null) {
    		Klinika klinika = klinikaService.findOne(lekarInfo.getIdKlinike());
    		if (klinika != null) {
    			lekar.setKlinika(klinika);
    		}
    		else {
        		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        	}
    	}
    	else {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	
        lekarService.save(lekar);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMINISTRATOR','LEKAR')")
    @PutMapping(consumes = "application/json", value = "/{id}")
    public ResponseEntity<Lekar> updateLekar(@PathVariable(name = "id") Long id, @RequestBody LekarBackendDTO lekarInfo){

        Lekar lekarOld = lekarService.findOne(id);
        if(lekarOld == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        lekarOld.setEmailKorisnika(lekarInfo.getEmailKorisnika());
        lekarOld.setImeKorisnika(lekarInfo.getImeKorisnika());
        lekarOld.setLozinkaKorisnika(passwordEncoder.encode(lekarInfo.getLozinkaKorisnika()));
        lekarOld.setPrezimeKorisnika(lekarInfo.getPrezimeKorisnika());
        
        // potrebna for petlja da se za svaki ID iz pregledi HashSet-a
        // pronadje objekat Pregled i onda doda u preglede za lekara
        
        //lekarOld.setPregledi(lekarInfo.getPregledi());
        
        // bad request ukoliko id klinike nije prosledjen ili id nije ispravan
    	if (lekarInfo.getIdKlinike() != null) {
    		Klinika klinika = klinikaService.findOne(lekarInfo.getIdKlinike());
    		if (klinika != null) {
    			lekarOld.setKlinika(klinika);
    		}
    		else {
        		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        	}
    	}
    	else {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}

        lekarOld = lekarService.save(lekarOld);

        return new ResponseEntity<>(lekarOld, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
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

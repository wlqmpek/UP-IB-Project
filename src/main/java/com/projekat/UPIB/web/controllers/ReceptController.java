package com.projekat.UPIB.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projekat.UPIB.models.Recept;
import com.projekat.UPIB.services.IPregledService;
import com.projekat.UPIB.services.IReceptService;
import com.projekat.UPIB.web.dto.recept.ReceptBackendDTO;
import com.projekat.UPIB.web.dto.recept.ReceptFrontendDTO;


@CrossOrigin(origins = "https://localhost:3000")
@RestController
@RequestMapping(value = "/Recepti")
public class ReceptController {
	@Autowired
    private IReceptService receptService;
	
	@Autowired
    private IPregledService pregledService;
	
	@PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping
    public ResponseEntity<List<ReceptFrontendDTO>> findAll(){

        List<Recept> recepti = receptService.findAll();
        List<ReceptFrontendDTO> receptiFrontendDTO = new ArrayList<ReceptFrontendDTO>();
        
        for (Recept recept : recepti) {
        	ReceptFrontendDTO receptFrontendDTO = new ReceptFrontendDTO(recept);
        	receptiFrontendDTO.add(receptFrontendDTO);
        }
        
        return new ResponseEntity<List<ReceptFrontendDTO>>(receptiFrontendDTO, HttpStatus.OK);
    }

	@PreAuthorize("hasAnyRole('ADMINISTRATOR','MEDICINSKA_SESTRA','LEKAR')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ReceptFrontendDTO> findOne(@PathVariable(name = "id") Long id){

    	Recept recept = receptService.findOne(id);
        if(recept == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ReceptFrontendDTO receptFrontendDTO = new ReceptFrontendDTO(recept);

        return new ResponseEntity<ReceptFrontendDTO>(receptFrontendDTO, HttpStatus.OK);
    }

	@PreAuthorize("hasAnyRole('ADMINISTRATOR','LEKAR')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<ReceptFrontendDTO> saveRecept(@RequestBody ReceptBackendDTO receptBackendDTO){

    	Recept recept = new Recept();
    	recept.setOpisRecepta(receptBackendDTO.getOpisRecepta());
    	if (receptBackendDTO.getIdPregleda() != null) {
    		recept.setPregled(pregledService.findOne(receptBackendDTO.getIdPregleda()));
    	}
    	else {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	recept.setOveren(receptBackendDTO.isOveren());
    	
    	ReceptFrontendDTO receptFrontendDTO = new ReceptFrontendDTO(recept);
    	
    	receptService.save(recept);
        return new ResponseEntity<ReceptFrontendDTO>(receptFrontendDTO,HttpStatus.CREATED);
    }
    
	@PreAuthorize("hasAnyRole('ADMINISTRATOR','MEDICINSKA_SESTRA')")
    @PutMapping(consumes = "application/json", value = "/{id}")
    public ResponseEntity<Recept> updateRecept(@PathVariable(name = "id") Long id, @RequestBody ReceptBackendDTO receptBackendDTO){

    	Recept recept = receptService.findOne(id);
        if(recept == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        recept.setPregled(pregledService.findOne(receptBackendDTO.getIdPregleda()));
        recept.setOpisRecepta(receptBackendDTO.getOpisRecepta());
        recept.setOveren(true);

        recept = receptService.save(recept);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    
	@PreAuthorize("hasRole('ADMINISTRATOR')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePregled(@PathVariable(name = "id") Long id){

    	Recept recept = receptService.findOne(id);
    	
        if(recept == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        receptService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }      
}

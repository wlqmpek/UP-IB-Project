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
import com.projekat.UPIB.models.ZdravstveniKarton;
import com.projekat.UPIB.services.IZdravstveniKarton;
import com.projekat.UPIB.web.dto.zdravstveniKarton.ZdravstveniKartonBackendDTO;
import com.projekat.UPIB.web.dto.zdravstveniKarton.ZdravstveniKartonFrontendDTO;


@CrossOrigin(origins = "https://localhost:3000")
@RestController
@RequestMapping(value = "/ZdravstveniKartoni")
public class ZdravstveniKartonController {
	
	@Autowired
    private IZdravstveniKarton zdravstveniKartonService;
	
	@PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping
    public ResponseEntity<List<ZdravstveniKartonFrontendDTO>> findAll(){

        List<ZdravstveniKarton> zdravstveniKartoni = zdravstveniKartonService.findAll();
        List<ZdravstveniKartonFrontendDTO> zdravstveniKartoniFrontendDTO = new ArrayList<ZdravstveniKartonFrontendDTO>();
        
        for (ZdravstveniKarton zdravstveniKarton : zdravstveniKartoni) {
        	ZdravstveniKartonFrontendDTO zdravstveniKartonFrontendDTO = new ZdravstveniKartonFrontendDTO(zdravstveniKarton);
        	zdravstveniKartoniFrontendDTO.add(zdravstveniKartonFrontendDTO);
        }
        
        return new ResponseEntity<List<ZdravstveniKartonFrontendDTO>>(zdravstveniKartoniFrontendDTO, HttpStatus.OK);
    }

	@PreAuthorize("hasAnyRole('ADMINISTRATOR','LEKAR','PACIJENT')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ZdravstveniKartonFrontendDTO> findOne(@PathVariable(name = "id") Long id){

    	ZdravstveniKarton zdravstveniKarton = zdravstveniKartonService.findOne(id);
        if(zdravstveniKarton == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ZdravstveniKartonFrontendDTO zdravstveniKartonFrontendDTO = new ZdravstveniKartonFrontendDTO(zdravstveniKarton);

        return new ResponseEntity<ZdravstveniKartonFrontendDTO>(zdravstveniKartonFrontendDTO, HttpStatus.OK);
    }

	@PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<ZdravstveniKartonFrontendDTO> saveZdravstveniKarton(@RequestBody ZdravstveniKartonBackendDTO zdravstveniKartonBackendDTO){

    	ZdravstveniKarton zdravstveniKarton = new ZdravstveniKarton();
    	zdravstveniKarton.setVisina(zdravstveniKartonBackendDTO.getVisina());
        zdravstveniKarton.setTezina(zdravstveniKartonBackendDTO.getTezina());
        zdravstveniKarton.setKrvnaGrupa(zdravstveniKartonBackendDTO.getKrvnaGrupa());
        zdravstveniKarton.setDioptrija(zdravstveniKartonBackendDTO.getDioptrija());
        zdravstveniKarton.setAlergije(zdravstveniKartonBackendDTO.getAlergije());
        zdravstveniKarton.setPacijent(null);
    	
    	ZdravstveniKartonFrontendDTO zdravstveniKartonFrontendDTO = new ZdravstveniKartonFrontendDTO(zdravstveniKarton);
    	
    	zdravstveniKartonService.save(zdravstveniKarton);
        return new ResponseEntity<ZdravstveniKartonFrontendDTO>(zdravstveniKartonFrontendDTO,HttpStatus.CREATED);
    }
    
	@PreAuthorize("hasAnyRole('ADMINISTRATOR','LEKAR')")
    @PutMapping(consumes = "application/json", value = "/{id}")
    public ResponseEntity<Recept> updateZdravstveniKarton(@PathVariable(name = "id") Long id, @RequestBody ZdravstveniKartonBackendDTO zdravstveniKartonBackendDTO){

    	ZdravstveniKarton zdravstveniKarton = zdravstveniKartonService.findOne(id);
        if(zdravstveniKarton == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        zdravstveniKarton.setVisina(zdravstveniKartonBackendDTO.getVisina());
        zdravstveniKarton.setTezina(zdravstveniKartonBackendDTO.getTezina());
        zdravstveniKarton.setKrvnaGrupa(zdravstveniKartonBackendDTO.getKrvnaGrupa());
        zdravstveniKarton.setDioptrija(zdravstveniKartonBackendDTO.getDioptrija());
        zdravstveniKarton.setAlergije(zdravstveniKartonBackendDTO.getAlergije());
        
        zdravstveniKarton = zdravstveniKartonService.save(zdravstveniKarton);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    
	@PreAuthorize("hasRole('ADMINISTRATOR')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteZdravstveniKarton(@PathVariable(name = "id") Long id){

    	ZdravstveniKarton zdravstveniKarton = zdravstveniKartonService.findOne(id);
    	
        if(zdravstveniKarton == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        zdravstveniKartonService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }      
}
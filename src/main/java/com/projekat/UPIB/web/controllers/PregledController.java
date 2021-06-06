package com.projekat.UPIB.web.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import com.projekat.UPIB.web.dto.PregledBackendDTO;
import com.projekat.UPIB.web.dto.PregledFrontendDTO;
import com.projekat.UPIB.models.Pregled;
import com.projekat.UPIB.models.Recept;
import com.projekat.UPIB.services.IKlinikaService;
import com.projekat.UPIB.services.ILekarService;
import com.projekat.UPIB.services.IMedicinskaSestraService;
import com.projekat.UPIB.services.IPregledService;
import com.projekat.UPIB.services.IReceptService;
import com.projekat.UPIB.services.IZdravstveniKarton;
import com.projekat.UPIB.services.implementation.ReceptService;



@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/Pregledi")
public class PregledController {
	@Autowired
    private IPregledService pregledService;
	
	@Autowired
    private IKlinikaService klinikaService;
	
	@Autowired
    private ILekarService lekarService;
	
	@Autowired
	private IMedicinskaSestraService medSestraService;
	
	@Autowired
	private IZdravstveniKarton zdravstveniKartonService;
	
	@Autowired
    private IReceptService receptService;
	
	
	@PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping
    public ResponseEntity<List<PregledFrontendDTO>> findAll(){

        List<Pregled> pregledi = pregledService.findAll();
        List<PregledFrontendDTO> preglediFrontendDTO = new ArrayList<PregledFrontendDTO>();
        
        for (Pregled pregled : pregledi) {
        	PregledFrontendDTO pregledFrontendDTO = new PregledFrontendDTO(pregled);
        	preglediFrontendDTO.add(pregledFrontendDTO);
        }
        
        return new ResponseEntity<List<PregledFrontendDTO>>(preglediFrontendDTO, HttpStatus.OK);
    }

	@PreAuthorize("hasRole('ADMINISTRATOR','LEKAR','MEDICINSKA_SESTRA','PACIJENT')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<PregledFrontendDTO> findOne(@PathVariable(name = "id") Long id){

    	Pregled pregled = pregledService.findOne(id);
        if(pregled == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        PregledFrontendDTO pregledFrontendDTO = new PregledFrontendDTO(pregled);

        return new ResponseEntity<PregledFrontendDTO>(pregledFrontendDTO, HttpStatus.OK);
    }

	@PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Pregled> savePregled(@RequestBody PregledBackendDTO pregledBackendDTO){

    	Pregled pregled = new Pregled();
    	pregled.setCena(pregledBackendDTO.getCena());
        pregled.setDijagnoza(pregledBackendDTO.getDijagnoza());
        pregled.setKlinika(klinikaService.findOne(pregledBackendDTO.getIdKlinike()));
        pregled.setLekar(lekarService.findOne(pregledBackendDTO.getIdLekara()));
        pregled.setMedicinskaSestra(medSestraService.findOne(pregledBackendDTO.getIdMedicinskeSestre()));
        pregled.setOpis(pregledBackendDTO.getOpis());
        pregled.setPocetakTermina(pregledBackendDTO.getPocetakTermina());
        pregled.setKrajTermima(pregledBackendDTO.getKrajTermina());
        pregled.setPopust(pregledBackendDTO.getPopust());
        pregled.setZdravstveniKarton(zdravstveniKartonService.findOne(pregledBackendDTO.getIdZdravstvenogKartona()));
    	
    	
    	pregledService.save(pregled);
        return new ResponseEntity<>(pregled, HttpStatus.CREATED);
    }

	@PreAuthorize("hasRole('ADMINISTRATOR','LEKAR')")
    @PutMapping(consumes = "application/json", value = "/{id}")
    public ResponseEntity<Pregled> updatePregled(@PathVariable(name = "id") Long id, @RequestBody PregledBackendDTO pregledBackendDTO){

    	Pregled pregled = pregledService.findOne(id);
        if(pregled == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        pregled.setCena(pregledBackendDTO.getCena());
        pregled.setDijagnoza(pregledBackendDTO.getDijagnoza());
        pregled.setKlinika(klinikaService.findOne(pregledBackendDTO.getIdKlinike()));
        pregled.setLekar(lekarService.findOne(pregledBackendDTO.getIdLekara()));
        pregled.setMedicinskaSestra(medSestraService.findOne(pregledBackendDTO.getIdMedicinskeSestre()));
        pregled.setOpis(pregledBackendDTO.getOpis());
        pregled.setPocetakTermina(pregledBackendDTO.getPocetakTermina());
        pregled.setKrajTermima(pregledBackendDTO.getKrajTermina());
        pregled.setPopust(pregledBackendDTO.getPopust());
        pregled.setZdravstveniKarton(zdravstveniKartonService.findOne(pregledBackendDTO.getIdZdravstvenogKartona()));

        pregled = pregledService.save(pregled);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    
	@PreAuthorize("hasRole('ADMINISTRATOR')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePregled(@PathVariable(name = "id") Long id){

    	Pregled pregled = pregledService.findOne(id);
    	
        if(pregled == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        pregledService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }      
}

package com.projekat.UPIB.web.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import com.projekat.UPIB.models.Administrator;
import com.projekat.UPIB.models.Lekar;
import com.projekat.UPIB.models.MedicinskaSestra;
import com.projekat.UPIB.services.*;
import com.projekat.UPIB.web.dto.pregled.PregledKreiranjeAdminDTO;
import com.projekat.UPIB.web.dto.pregled.PregledKreiranjeLekarDTO;
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

import com.projekat.UPIB.web.dto.pregled.PregledBackendDTO;
import com.projekat.UPIB.web.dto.pregled.PregledFrontendDTO;
import com.projekat.UPIB.models.Pregled;


@CrossOrigin(origins = "https://localhost:3000")
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

	@Autowired
    private IAdministratorService iAdministratorService;
	
	

	@PreAuthorize("hasAnyRole('ADMINISTRATOR', 'LEKAR', 'MEDICINSKA_SESTRA')")
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

	@PreAuthorize("hasAnyRole('ADMINISTRATOR','LEKAR','MEDICINSKA_SESTRA','PACIJENT', 'KLINICKI_ADMINISTRATOR')")
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

	@PreAuthorize("hasAnyRole('ADMINISTRATOR','LEKAR','MEDICINSKA_SESTRA')")
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
    
	@PreAuthorize("hasAnyRole('ADMINISTRATOR', 'KLINICKI_ADMINISTRATOR')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePregled(@PathVariable(name = "id") Long id){

    	Pregled pregled = pregledService.findOne(id);
    	
        if(pregled == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        pregledService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('KLINICKI_ADMINISTRATOR')")
    @GetMapping("/klinika")
    public ResponseEntity<List<PregledFrontendDTO>> getPreglediForKlinika(Principal principal){

        Administrator administrator = iAdministratorService.findAdministratorByEmailKorisnika(principal.getName());

        if(administrator == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Pregled> pregledi = pregledService.findAllByKlinika(administrator.getKlinika().getIdKlinike());
        List<PregledFrontendDTO> retVal = new ArrayList<>();

        for (Pregled pregled : pregledi){
            retVal.add(new PregledFrontendDTO(pregled));
        }

        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('LEKAR')")
    @PostMapping("/pregled/lekar")
    public ResponseEntity<Void> addFreeAppointment(Principal principal, @RequestBody PregledKreiranjeLekarDTO pregledKreiranjeLekarDTO){

        Lekar lekar = lekarService.findLekarByEmailKorisnika(principal.getName());
        MedicinskaSestra medicinskaSestra = medSestraService
                .findMedicinskaSestraByEmailKorisnika(pregledKreiranjeLekarDTO.getMedSestraEmail());
        Pregled pregled = new Pregled();

        if(lekar == null || medicinskaSestra == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        pregled.setCena(pregledKreiranjeLekarDTO.getCena());
        pregled.setKlinika(lekar.getKlinika());
        pregled.setLekar(lekar);
        pregled.setMedicinskaSestra(medicinskaSestra);
        pregled.setPocetakTermina(pregledKreiranjeLekarDTO.getPocetakTermina());
        pregled.setKrajTermima(pregledKreiranjeLekarDTO.getKrajTermina());

        pregledService.save(pregled);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('KLINICKI_ADMINISTRATOR')")
    @PostMapping("/pregled/administrator")
    public ResponseEntity<Void> addFreeAppointmentAdmin(Principal principal, @RequestBody PregledKreiranjeAdminDTO pregledKreiranjeLekarDTO){

        Administrator administrator = iAdministratorService.findAdministratorByEmailKorisnika(principal.getName());
        MedicinskaSestra medicinskaSestra = medSestraService
                .findMedicinskaSestraByEmailKorisnika(pregledKreiranjeLekarDTO.getMedSestraEmail());
        Lekar lekar = lekarService.findLekarByEmailKorisnika(pregledKreiranjeLekarDTO.getLekarEmail());
        Pregled pregled = new Pregled();

        if(lekar == null || medicinskaSestra == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        pregled.setCena(pregledKreiranjeLekarDTO.getCena());
        pregled.setKlinika(administrator.getKlinika());
        pregled.setLekar(lekar);
        pregled.setMedicinskaSestra(medicinskaSestra);
        pregled.setPocetakTermina(pregledKreiranjeLekarDTO.getPocetakTermina());
        pregled.setKrajTermima(pregledKreiranjeLekarDTO.getKrajTermina());

        pregledService.save(pregled);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

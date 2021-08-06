package com.projekat.UPIB.web.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.projekat.UPIB.models.OceneKlinike;
import com.projekat.UPIB.models.Pacijent;
import com.projekat.UPIB.services.IOceneKlinikeService;
import com.projekat.UPIB.services.IPacijentService;
import com.projekat.UPIB.support.converters.klinika.KlinikaToKlinikaFrontDto;
import com.projekat.UPIB.web.dto.ParametriPretrageKlinikaDto;
import com.projekat.UPIB.web.dto.klinika.AvgOcenaKlinikeDTO;
import com.projekat.UPIB.web.dto.klinika.KlinikaListaDTO;
import com.projekat.UPIB.web.dto.klinika.OcenaKlinikeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.projekat.UPIB.web.dto.klinika.KlinikaFrontDto;
import com.projekat.UPIB.models.Administrator;
import com.projekat.UPIB.models.Klinika;
import com.projekat.UPIB.services.IAdministratorService;
import com.projekat.UPIB.services.IKlinikaService;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;

@CrossOrigin(origins = "https://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
@RequestMapping(value = "/Klinike")
public class KlinikaController {
	
	@Autowired
    private IKlinikaService klinikaService;
	
	@Autowired
    private IAdministratorService administratorService;

	@Autowired
    private IPacijentService pacijentService;

	@Autowired
    private IOceneKlinikeService oceneKlinikeService;

	@Autowired
    private KlinikaToKlinikaFrontDto klinikaToKlinikaFrontDto;

	@PreAuthorize("hasAnyRole('ADMINISTRATOR','PACIJENT')")
    @GetMapping
    public ResponseEntity<List<KlinikaFrontDto>> findAll(){

        List<Klinika> klinike = klinikaService.findAll();
        List<KlinikaFrontDto> klinikeFrontendDTO = new ArrayList<KlinikaFrontDto>();
        
        for (Klinika klinika : klinike) {
        	KlinikaFrontDto klinikaFrontendDTO = klinikaToKlinikaFrontDto.convert(klinika);
        	klinikeFrontendDTO.add(klinikaFrontendDTO);
        }
        
        return new ResponseEntity<List<KlinikaFrontDto>>(klinikeFrontendDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR','LEKAR','PACIJENT','MEDICINSKA_SESTRA', 'KLINICKI_ADMINISTRATOR')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<KlinikaFrontDto> findOne(@PathVariable(name = "id") Long id){

    	Klinika klinika = klinikaService.findOne(id);
        if(klinika == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        KlinikaFrontDto klinikaFrontendDTO = klinikaToKlinikaFrontDto.convert(klinika);

        return new ResponseEntity<KlinikaFrontDto>(klinikaFrontendDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Klinika> saveKlinika(@RequestBody Klinika klinika){

    	klinikaService.save(klinika);
        return new ResponseEntity<>(klinika, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMINISTRATOR, KLINICKI_ADMINISTRATOR')")
    @PutMapping(consumes = "application/json", value = "/{id}")
    public ResponseEntity<Klinika> updateKlinika(@PathVariable(name = "id") Long id, @RequestBody Klinika klinika){

    	Klinika klinikaOld = klinikaService.findOne(id);
        if(klinikaOld == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        klinikaOld.setNaziv(klinika.getNaziv());
        klinikaOld.setOpis(klinika.getOpis());
        klinikaOld.setAdresa(klinika.getAdresa());
        klinikaOld.setSlobodniTermini(klinika.getSlobodniTermini());
        klinikaOld.setAdministratori(klinika.getAdministratori());

        klinikaOld = klinikaService.save(klinikaOld);

        return new ResponseEntity<>(klinikaOld, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PutMapping(value = "/{idKlinike}/dodajAdmina/{idAdmina}", consumes = "application/json")
    public  ResponseEntity<Administrator> dodajAdminaUKliniku(@PathVariable(name = "idKlinike") Long idKlinike,
    		@PathVariable(name = "idAdmina") Long idAdmina){

        Administrator admin = administratorService.findOne(idAdmina);
        Klinika klinika = klinikaService.findOne(idKlinike);
        if(admin == null || klinika == null){
            return new ResponseEntity<Administrator>(HttpStatus.BAD_REQUEST);
        }

        admin.setKlinika(klinika);

        admin = administratorService.save(admin);
        klinika.getAdministratori().add(admin);

        return new ResponseEntity<Administrator>(admin, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteKlinika(@PathVariable(name = "id") Long id){

    	Klinika klinika = klinikaService.findOne(id);
        if(klinika == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        klinikaService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('KLINICKI_ADMINISTRATOR')")
    @GetMapping("/lista")
    public ResponseEntity<List<KlinikaListaDTO>> getLista(){

        List<Klinika> klinike = klinikaService.findAll();
        List<KlinikaListaDTO> retVal = new ArrayList<>();

        for(Klinika klinika : klinike){
            retVal.add(new KlinikaListaDTO(klinika));
        }

        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PermitAll
    @GetMapping("/ocene/{id}")
    public ResponseEntity<AvgOcenaKlinikeDTO> prosecnaOcena(@PathVariable Long id){

        AvgOcenaKlinikeDTO ocenaKlinikeDTO = new AvgOcenaKlinikeDTO();
        Klinika klinika = klinikaService.findOne(id);
        Double ocena = oceneKlinikeService.avgOcena(id);
        if(klinika == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ocenaKlinikeDTO.setIdKlinike(klinika.getIdKlinike());
        ocenaKlinikeDTO.setOcena(ocena);

        if(ocena == null){
            ocenaKlinikeDTO.setOcena(0.0);
        }

        ocenaKlinikeDTO.setNaziv(klinika.getNaziv());

        return new ResponseEntity<>(ocenaKlinikeDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PACIJENT')")
    @PostMapping("/ocene/oceni")
    public ResponseEntity<Void> oceniKliniku(@RequestBody OcenaKlinikeDTO ocenaKlinikeDTO){

        OceneKlinike oceneKlinike = new OceneKlinike();
        Klinika klinika = klinikaService.findOne(ocenaKlinikeDTO.getIdKlinike());
        Pacijent pacijent = pacijentService.findOne(ocenaKlinikeDTO.getIdPacijenta());

        if(ocenaKlinikeDTO == null && klinika == null && pacijent == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        oceneKlinike.setKlinika(klinika);
        oceneKlinike.setOcena(ocenaKlinikeDTO.getOcena());
        oceneKlinike.setPacijent(pacijent);

        oceneKlinikeService.save(oceneKlinike);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}

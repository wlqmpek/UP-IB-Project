package com.projekat.UPIB.web.controllers;

import com.projekat.UPIB.models.*;
import com.projekat.UPIB.services.*;
import com.projekat.UPIB.web.dto.lekar.*;
import com.projekat.UPIB.web.dto.korisnik.PasswordChangeDTO;
import com.projekat.UPIB.web.dto.pregled.PregledKreiranjeLekarDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "https://localhost:3000")
@RequestMapping(value = "/Lekari")
public class LekarController {

    private static final Long ROLE_LEKAR = 2L;

    @Autowired
    private ILekarService lekarService;
    
    @Autowired
    private IKlinikaService klinikaService;

    @Autowired
    private IAuthorityService authorityService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IOceneLekaraService oceneLekaraService;

    @Autowired
    private IPacijentService pacijentService;

    @Autowired
    private IAdministratorService administratorService;

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

    @PreAuthorize("hasAnyRole('ADMINISTRATOR','LEKAR', 'KLINICKI_ADMINISTRATOR')")
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
    	lekar.setAuthorities(authorityService.findByIdAuthority(ROLE_LEKAR));

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

    @PreAuthorize("hasAnyRole('ADMINISTRATOR','LEKAR')")
    @PutMapping(consumes = "application/json", value = "/{id}")
    public ResponseEntity<Lekar> updateLekar(@PathVariable(name = "id") Long id, @RequestBody LekarBackendDTO lekarInfo){

        Lekar lekarOld = lekarService.findOne(id);
        if(lekarOld == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        lekarOld.setEmailKorisnika(lekarInfo.getEmailKorisnika());
        lekarOld.setImeKorisnika(lekarInfo.getImeKorisnika());
//        lekarOld.setLozinkaKorisnika(passwordEncoder.encode(lekarInfo.getLozinkaKorisnika()));
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

    @PreAuthorize("hasRole('LEKAR')")
    @GetMapping(value = "/nadji/{email}")
    public ResponseEntity<LekarFrontendDTO> getLekarByEmail(@PathVariable(name = "email") String email){

        Lekar lekar = lekarService.findLekarByEmailKorisnika(email);
        if(lekar == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new LekarFrontendDTO(lekar), HttpStatus.OK);
    }

    @PermitAll
    @GetMapping("/ocene/{id}")
    public ResponseEntity<AvgOcenaLekaraDTO> prosecnaOcena(@PathVariable Long id){

        AvgOcenaLekaraDTO ocenaLekaraDTO = new AvgOcenaLekaraDTO();
        Lekar lekar = lekarService.findOne(id);
        Double ocena = oceneLekaraService.avgOcena(id);

        if(lekar == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ocenaLekaraDTO.setIme(lekar.getImeKorisnika());
        ocenaLekaraDTO.setPrezime(lekar.getPrezimeKorisnika());
        ocenaLekaraDTO.setOcena(ocena);
        if(ocena == null){
            ocenaLekaraDTO.setOcena(0.0);
        }

        return new ResponseEntity<>(ocenaLekaraDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PACIJENT')")
    @PostMapping("/ocene/oceni")
    public ResponseEntity<Void> oceni(@RequestBody OcenaLekaraDTO ocenaLekaraDTO){

        OceneDoktora oceneDoktora = new OceneDoktora();
        Pacijent pacijent = pacijentService.findOne(ocenaLekaraDTO.getIdPacijenta());
        Lekar lekar = lekarService.findOne(ocenaLekaraDTO.getIdLekara());

        if(lekar == null || pacijent == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        oceneDoktora.setLekar(lekar);
        oceneDoktora.setOcena(ocenaLekaraDTO.getOcena());
        oceneDoktora.setPacijent(pacijent);

        oceneLekaraService.save(oceneDoktora);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('KLINICKI_ADMINISTRATOR')")
    @GetMapping("/lista")
    public ResponseEntity<List<LekarListaDTO>> getLekariLista(Principal principal){

        LekarListaDTO lekarListaDTO;
        List<Lekar> lekari = lekarService.findAll();
        Administrator administrator = administratorService.findAdministratorByEmailKorisnika(principal.getName());

        List<LekarListaDTO> retVal = new ArrayList<>();

        for (Lekar lekar : lekari){
            if(lekar.getKlinika().getIdKlinike() == administrator.getKlinika().getIdKlinike()){
                lekarListaDTO = new LekarListaDTO();
                lekarListaDTO.setLabel(lekar.getImeKorisnika() + " " + lekar.getPrezimeKorisnika());
                lekarListaDTO.setValue(lekar.getEmailKorisnika());
                retVal.add(lekarListaDTO);
            }
        }

        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('KLINICKI_ADMINISTRATOR')")
    @GetMapping("/klinika")
    public ResponseEntity<List<LekarFrontendDTO>> getLekareByKlinika(Principal principal) {

        Administrator administrator = administratorService.findAdministratorByEmailKorisnika(principal.getName());
        if(administrator == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Lekar> lekari = lekarService.findLekarByKlinika(administrator.getKlinika().getIdKlinike());
        List<LekarFrontendDTO> retVal = new ArrayList<>();

        for (Lekar lekar : lekari) {
            retVal.add(new LekarFrontendDTO(lekar));
        }

        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }
}

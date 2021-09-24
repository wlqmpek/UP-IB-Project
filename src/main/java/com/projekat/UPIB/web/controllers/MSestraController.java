package com.projekat.UPIB.web.controllers;

import com.projekat.UPIB.models.Administrator;
import com.projekat.UPIB.models.Klinika;
import com.projekat.UPIB.models.Lekar;
import com.projekat.UPIB.services.*;
import com.projekat.UPIB.web.dto.medicinkaSestra.MSestraListDTO;
import com.projekat.UPIB.web.dto.medicinkaSestra.MedicinskaSestraDTO;
import com.projekat.UPIB.models.MedicinskaSestra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "https://localhost:3000")
@RequestMapping("/MedicinskeSestre")
public class MSestraController {

    private static final Long ROLE_M_SESTRA = 3L;
    @Autowired
    private IMedicinskaSestraService sestraService;

    @Autowired
    private IKlinikaService klinikaService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IAuthorityService authorityService;

    @Autowired
    private ILekarService lekarService;

    @Autowired
    private IAdministratorService administratorService;

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping
    public ResponseEntity<List<MedicinskaSestraDTO>> getAll(){

        List<MedicinskaSestra> medicinskeSestre = sestraService.findAll();
        List<MedicinskaSestraDTO> retVal = new ArrayList<>();

        for (MedicinskaSestra sestra : medicinskeSestre){
            retVal.add(new MedicinskaSestraDTO(sestra));
        }

        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR','MEDICINSKA_SESTRA', 'KLINICKI_ADMINISTRATOR', 'PACIJENT')")
    @GetMapping("/{id}")
    public ResponseEntity<MedicinskaSestraDTO> getOne(@PathVariable("id") Long id){

        MedicinskaSestra medicinskaSestra = sestraService.findOne(id);
        if(medicinskaSestra == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new MedicinskaSestraDTO(medicinskaSestra), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<MedicinskaSestraDTO> createMSestra(@RequestBody MedicinskaSestraDTO medicinskaSestraDTO){

        MedicinskaSestra medicinskaSestra = new MedicinskaSestra();
        Klinika klinika = klinikaService.findOne(medicinskaSestraDTO.getIdKlinike());
        if (klinika == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        medicinskaSestra.setImeKorisnika(medicinskaSestraDTO.getImeKorisnika());
        medicinskaSestra.setPrezimeKorisnika(medicinskaSestraDTO.getPrezimeKorisnika());
        medicinskaSestra.setEmailKorisnika(medicinskaSestraDTO.getEmailKorisnika());
        medicinskaSestra.setLozinkaKorisnika(passwordEncoder.encode(medicinskaSestraDTO.getLozinka()));
        medicinskaSestra.setKlinika(klinika);
        medicinskaSestra.setAuthorities(authorityService.findByIdAuthority(ROLE_M_SESTRA));
        //medicinskaSestra.setPregledi(new HashSet<>());

        medicinskaSestra = sestraService.save(medicinskaSestra);
        medicinskaSestraDTO = new MedicinskaSestraDTO(medicinskaSestra);
        return new ResponseEntity<>(medicinskaSestraDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR','MEDICINSKA_SESTRA')")
    @PutMapping(consumes = "application/json", value = "/{id}")
    public ResponseEntity<MedicinskaSestraDTO> editMSestra(@PathVariable("id") Long id,
                                                           @RequestBody MedicinskaSestraDTO medicinskaSestraDTO){

        MedicinskaSestra medicinskaSestra = sestraService.findOne(id);
        if(medicinskaSestra == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        medicinskaSestra.setImeKorisnika(medicinskaSestraDTO.getImeKorisnika());
        medicinskaSestra.setPrezimeKorisnika(medicinskaSestraDTO.getPrezimeKorisnika());
        medicinskaSestra.setEmailKorisnika(medicinskaSestraDTO.getEmailKorisnika());
//        medicinskaSestra.setLozinkaKorisnika(passwordEncoder.encode(medicinskaSestraDTO.getLozinkaKorisnika()));
        medicinskaSestra.setKlinika(klinikaService.findOne(medicinskaSestraDTO.getIdKlinike()));

        medicinskaSestra = sestraService.save(medicinskaSestra);
        medicinskaSestraDTO = new MedicinskaSestraDTO(medicinskaSestra);
        return new ResponseEntity<>(medicinskaSestraDTO, HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteMSestra(@PathVariable("id") Long id){

        if(sestraService.findOne(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        sestraService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('LEKAR, KLINICKI_ADMINISTRATOR')")
    @GetMapping("/lista")
    public ResponseEntity<List<MSestraListDTO>> getMSestreLista(Principal principal){

        Long id = null;
        Lekar lekar = lekarService.findLekarByEmailKorisnika(principal.getName());
        Administrator administrator = administratorService.findAdministratorByEmailKorisnika(principal.getName());

        if(lekar != null){
            id = lekar.getKlinika().getIdKlinike();
        }
        if(administrator != null){
            id = administrator.getKlinika().getIdKlinike();
        }
        MSestraListDTO mSestraListDTO;
        List<MSestraListDTO> retVal = new ArrayList<>();
        List<MedicinskaSestra> medicinskeSestre = sestraService.getAllForList(id);

        for (MedicinskaSestra sestra : medicinskeSestre) {
            mSestraListDTO = new MSestraListDTO();
            mSestraListDTO.setLabel(sestra.getImeKorisnika() + " " + sestra.getPrezimeKorisnika());
            mSestraListDTO.setValue(sestra.getEmailKorisnika());
            retVal.add(mSestraListDTO);
        }

        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }
}

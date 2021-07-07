package com.projekat.UPIB.web.controllers;

import com.projekat.UPIB.models.Administrator;
import com.projekat.UPIB.models.Cenovnik;
import com.projekat.UPIB.models.Lekar;
import com.projekat.UPIB.services.IAdministratorService;
import com.projekat.UPIB.services.ICenovnikService;
import com.projekat.UPIB.services.ILekarService;
import com.projekat.UPIB.services.implementation.CenovnikService;
import com.projekat.UPIB.web.dto.cenovnik.CenovnikDTO;
import com.projekat.UPIB.web.dto.cenovnik.CenovnikListaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/cenovnik")
public class CenovnikController {

    @Autowired
    private IAdministratorService administratorService;

    @Autowired
    private ILekarService lekarService;

    @Autowired
    private ICenovnikService cenovnikService;

    @PreAuthorize("hasAnyRole('KLINICKI_ADMINISTRATOR, LEKAR')")
    @GetMapping
    public ResponseEntity<List<CenovnikDTO>> getCenovnikForKlinika(Principal principal){

        List<CenovnikDTO> retVal = new ArrayList<>();

        Administrator administrator = administratorService.findAdministratorByEmailKorisnika(principal.getName());
        if(administrator != null){
            List<Cenovnik> cenovnik = cenovnikService.getByClinic(administrator.getKlinika().getIdKlinike());
            for (Cenovnik cenovnik1 : cenovnik) {
                retVal.add(new CenovnikDTO(cenovnik1));
            }
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        }

        Lekar lekar = lekarService.findLekarByEmailKorisnika(principal.getName());
        if(lekar != null){
            List<Cenovnik> cenovnik = cenovnikService.getByClinic(lekar.getKlinika().getIdKlinike());
            for (Cenovnik cenovnik1 : cenovnik) {
                retVal.add(new CenovnikDTO(cenovnik1));
            }
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasAnyRole('KLINICKI_ADMINISTRATOR')")
    @PostMapping
    public ResponseEntity<CenovnikDTO> postCenovnik(@RequestBody CenovnikDTO cenovnikDTO, Principal principal){

        Cenovnik cenovnik = new Cenovnik();

        Administrator administrator = administratorService.findAdministratorByEmailKorisnika(principal.getName());
        if(administrator == null){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        cenovnik.setCena(cenovnikDTO.getCena());
        cenovnik.setKlinika(administrator.getKlinika());
        cenovnik.setNaziv(cenovnikDTO.getNaziv());

        cenovnik = cenovnikService.save(cenovnik);

        return new ResponseEntity<>(new CenovnikDTO(cenovnik), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('KLINICKI_ADMINISTRATOR')")
    @PutMapping("/{id}")
    public ResponseEntity<CenovnikDTO> putCenovnik(@PathVariable Long id, @RequestBody CenovnikDTO cenovnikDTO){

        Cenovnik cenovnik = cenovnikService.getCenovnik(id);
        if(cenovnik == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        cenovnik.setNaziv(cenovnikDTO.getNaziv());
        cenovnik.setCena(cenovnikDTO.getCena());

        cenovnik = cenovnikService.save(cenovnik);

        return new ResponseEntity<>(new CenovnikDTO(cenovnik), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('KLINICKI_ADMINISTRATOR, LEKAR')")
    @GetMapping("/lista")
    public ResponseEntity<List<CenovnikListaDTO>> getListaCenovnik(Principal principal){

        List<CenovnikListaDTO> retVal = new ArrayList<>();

        Administrator administrator = administratorService.findAdministratorByEmailKorisnika(principal.getName());
        if(administrator != null){
            List<Cenovnik> cenovnik = cenovnikService.getByClinic(administrator.getKlinika().getIdKlinike());
            for (Cenovnik cenovnik1 : cenovnik) {
                retVal.add(new CenovnikListaDTO(cenovnik1));
            }
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        }

        Lekar lekar = lekarService.findLekarByEmailKorisnika(principal.getName());
        if(lekar != null){
            List<Cenovnik> cenovnik = cenovnikService.getByClinic(lekar.getKlinika().getIdKlinike());
            for (Cenovnik cenovnik1 : cenovnik) {
                retVal.add(new CenovnikListaDTO(cenovnik1));
            }
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('KLINICKI_ADMINISTRATOR')")
    public ResponseEntity<Void> removeCenovnik(@PathVariable("id") Long id){

        Cenovnik cenovnik = cenovnikService.getCenovnik(id);
        if(cenovnik == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        cenovnikService.remove(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

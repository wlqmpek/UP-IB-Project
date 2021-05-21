package com.projekat.UPIB.web.controllers;

import com.projekat.UPIB.support.converters.PacijentEditDtoToPacijent;
import com.projekat.UPIB.support.converters.PacijentRegisterDtoToPacijent;
import com.projekat.UPIB.support.converters.PacijentToPacijentFrontDto;
import com.projekat.UPIB.web.dto.pacijent.PacijentFrontDTO;
import com.projekat.UPIB.web.dto.pacijent.PacijentEditDto;
import com.projekat.UPIB.web.dto.pacijent.PacijentLoginDTO;
import com.projekat.UPIB.web.dto.pacijent.PacijentRegisterDTO;
import com.projekat.UPIB.models.Pacijent;
import com.projekat.UPIB.services.IPacijentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/Pacijenti")
public class PacijentController {

    @Autowired
    private IPacijentService pacijentService;

    @Autowired
    private PacijentRegisterDtoToPacijent pacijentRegisterDtoToPacijent;

    @Autowired
    private PacijentEditDtoToPacijent pacijentEditDtoToPacijent;

    @Autowired
    private PacijentToPacijentFrontDto pacijentToPacijentFrontDto;

    @GetMapping
    public ResponseEntity<List<Pacijent>> findAll(){

        List<Pacijent> pacijenti = pacijentService.findAll();
        return new ResponseEntity<List<Pacijent>>(pacijenti, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PacijentFrontDTO> findOne(@PathVariable(name = "id") Long id){
        System.out.println("Get pogodjen " + id);
        ResponseEntity responseEntity = null;
        PacijentFrontDTO pacijentFrontDTO = pacijentToPacijentFrontDto.convert(pacijentService.findOne(id));

        responseEntity = (pacijentFrontDTO == null) ?
                new ResponseEntity(pacijentFrontDTO, HttpStatus.NOT_FOUND) : new ResponseEntity(pacijentFrontDTO, HttpStatus.OK);

        return responseEntity;
    }

    @PostMapping(consumes = "application/json")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Pacijent> savePacijent(@RequestBody PacijentRegisterDTO pacijentRegisterDTO){
        System.out.println("Pacijent dto" + pacijentRegisterDTO);
        ResponseEntity responseEntity = null;
        Pacijent pacijent = pacijentRegisterDtoToPacijent.convert(pacijentRegisterDTO);
        pacijent = pacijentService.save(pacijent);

        responseEntity = (pacijent == null) ?
                new ResponseEntity(pacijent, HttpStatus.BAD_REQUEST) : new ResponseEntity(pacijent, HttpStatus.CREATED);

        return responseEntity;
    }

    //TODO: Umesto da radimo convertovanje iz DTO U Entity i obrnuto
    //u kontroleru uraditi da se isto radi u konverteru primer se nalazi
    //u IB Vezva 7 oko 32min
    @PutMapping(consumes = "application/json", value = "/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Pacijent> updatePacijent(@PathVariable(name = "id") Long id, @RequestBody PacijentEditDto pacijentEditDto){

        ResponseEntity responseEntity = null;
        Pacijent pacijentOld = pacijentService.findOne(id);
            pacijentOld = pacijentEditDtoToPacijent.convert(pacijentOld, pacijentEditDto);
            pacijentOld = pacijentService.save(pacijentOld);
            responseEntity = (pacijentOld == null) ?
                    responseEntity = new ResponseEntity(pacijentOld, HttpStatus.BAD_REQUEST) : new ResponseEntity(pacijentOld, HttpStatus.OK);

            return responseEntity;
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePacijent(@PathVariable(name = "id") Long id) {
        Pacijent pacijent = pacijentService.findOne(id);
        if(pacijent == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        pacijentService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    //Komentar
//    @PostMapping(value = "/login", consumes = "application/json")
//    public ResponseEntity<PacijentFrontDTO> loginPacijent(@RequestBody PacijentLoginDTO pacijentLoginDTO) {
//
//        Pacijent pacijent = pacijentService.findPacijentByEmailKorisnika(pacijentLoginDTO.getEmailKorisnika());
//
//        if(pacijent != null && pacijent.getLozinkaKorisnika().equals(pacijentLoginDTO.getLozinkaKorisnika())) {
//
//            PacijentFrontDTO pacijentFrontDTO = new PacijentFrontDTO(pacijent);
//
//            return new ResponseEntity<>(pacijentFrontDTO, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//    }

    
    
}

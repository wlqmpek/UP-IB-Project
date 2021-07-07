package com.projekat.UPIB.web.controllers;

import com.projekat.UPIB.mail.EmailService;
import com.projekat.UPIB.models.Authority;
import com.projekat.UPIB.models.PacijentLink;
import com.projekat.UPIB.services.implementation.AuthorityService;
import com.projekat.UPIB.services.implementation.PacijentLinkService;
import com.projekat.UPIB.web.dto.pacijent.PacijentFrontDTO;
import com.projekat.UPIB.web.dto.pacijent.PacijentLinkDTO;
import com.projekat.UPIB.web.dto.pacijent.PacijentRegisterDTO;
import com.projekat.UPIB.enums.StatusKorisnika;
import com.projekat.UPIB.models.Pacijent;
import com.projekat.UPIB.services.implementation.PacijentService;
import com.projekat.UPIB.web.dto.pacijent.PacijentStatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@CrossOrigin(origins = "https://localhost:3000")
@RequestMapping(value = "/Zahtevi")
public class ZahteviZaRegistracijuController {


    @Autowired
    private EmailService emailService;

    @Autowired
    private PacijentService pacijentService;

    @Autowired
    private PacijentLinkService pacijentLinkService;

    @Autowired
    private AuthorityService authorityService;

    @GetMapping
    public ResponseEntity<List<PacijentFrontDTO>> getPacijents(){
        List<Pacijent> pacijenti = pacijentService.findAll();
        List<PacijentFrontDTO> retVal = new ArrayList<>();

        for (Pacijent pacijent : pacijenti) {
            if(pacijent.getStatusKorisnika().equals(StatusKorisnika.NA_CEKANJU)){
                PacijentFrontDTO frontDTO = new PacijentFrontDTO(pacijent);
                retVal.add(frontDTO);
            }
        }

        return  new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @GetMapping("/Nadji/{path}")
    public ResponseEntity<PacijentLinkDTO> getPacijentLink(@PathVariable("path") String path){

        PacijentLink pacijentLink = pacijentLinkService.findByPutanja(path);

        return new ResponseEntity<>(new PacijentLinkDTO(pacijentLink), HttpStatus.OK);
    }

    @PutMapping(consumes = "application/json", value = "/{id}")
    public ResponseEntity<PacijentStatusDTO> getPacijent(@PathVariable("id") long id,
                                                           @RequestBody PacijentStatusDTO pacijent) throws InvalidKeyException, NoSuchAlgorithmException {

        Pacijent retVal = pacijentService.findOne(id);
        if(retVal == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        retVal.setStatusKorisnika(pacijent.getStatusKorisnika());
        retVal = pacijentService.save(retVal);

        PacijentStatusDTO pacijentDTO = new PacijentStatusDTO(retVal);

        if(retVal.getStatusKorisnika() == StatusKorisnika.ODBIJEN){
            emailService.sendMessage(retVal.getEmailKorisnika(), "Registracija na KC Hipokrat",
                    "Vasa registracija je odbijena.");
            return new ResponseEntity<>(pacijentDTO, HttpStatus.OK);
        }
        emailService.sendMessage(retVal.getEmailKorisnika(), "Registracija na KC Hipokrat",
                "Molimo pritisnite na link da bi ste zavrsili registraciju \n"
                        +"http://localhost:3000/registracija/prihvati/"+ createPath(retVal.getEmailKorisnika()));

        return new ResponseEntity<>(pacijentDTO, HttpStatus.ACCEPTED);
    }

    @GetMapping("/prihvati/{path}")
    public ResponseEntity<Void> succesRegistration(@PathVariable("path") String path){
        //TODO provera da li je link validan ako jeste posalji ga na login page
        PacijentLink pacijentLink = pacijentLinkService.findByPutanja(path);
        if(pacijentLink.isValid()){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        if(pacijentLink.getDatumIsteka().isBefore(LocalDateTime.now())){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        Pacijent pacijent = pacijentService.findPacijentByEmailKorisnika(pacijentLink.getEmail());
        pacijent.setStatusKorisnika(StatusKorisnika.PRIHVACEN);
        pacijentService.save(pacijent);
        pacijentLink.setValid(false);
        pacijentLinkService.save(pacijentLink);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private String createPath(String email) throws NoSuchAlgorithmException, InvalidKeyException {
        //TODO kreiranje linka za registraciju pacijenta, poterbno je da se taj path hashuje

        String secret = "7alpbCtAsI";
        Mac sha = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        sha.init(secretKey);

        LocalDateTime datumIsteka = LocalDateTime.now().plusHours(1);
        PacijentLinkDTO linkDTO = new PacijentLinkDTO();

        String path = new String(Base64.getEncoder().encode(sha.doFinal(email.getBytes())));
        linkDTO.setDatumIsteka(datumIsteka);
        linkDTO.setValid(true);
        linkDTO.setPutanja(path);

        PacijentLink pacijentLink = new PacijentLink();
        pacijentLink.setEmail(email);
        pacijentLink.setDatumIsteka(datumIsteka);
        pacijentLink.setValid(true);
        pacijentLink.setPutanja(path);
        pacijentLinkService.save(pacijentLink);

        return path;
    }
}

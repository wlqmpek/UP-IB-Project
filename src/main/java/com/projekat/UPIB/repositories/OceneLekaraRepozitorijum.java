package com.projekat.UPIB.repositories;

import com.projekat.UPIB.models.OceneDoktora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OceneLekaraRepozitorijum extends JpaRepository<OceneDoktora, Long> {

    @Query("select avg(od.ocena) from OceneDoktora as od where od.lekar.idKorisnika = ?1")
    Double avgOcena(Long id);
}

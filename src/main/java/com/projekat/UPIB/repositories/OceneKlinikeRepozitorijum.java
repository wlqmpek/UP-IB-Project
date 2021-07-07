package com.projekat.UPIB.repositories;

import com.projekat.UPIB.models.OceneKlinike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OceneKlinikeRepozitorijum extends JpaRepository<OceneKlinike, Long> {

    @Query("select avg(ok.ocena) from OceneKlinike as ok where ok.klinika = ?1")
    Double avgOcena(Long id);
}

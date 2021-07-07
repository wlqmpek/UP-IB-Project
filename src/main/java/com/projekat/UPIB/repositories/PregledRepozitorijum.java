package com.projekat.UPIB.repositories;

import com.projekat.UPIB.models.Pregled;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PregledRepozitorijum extends JpaRepository<Pregled, Long> {
    List<Pregled> findByKlinikaIdKlinike (Long id);
}

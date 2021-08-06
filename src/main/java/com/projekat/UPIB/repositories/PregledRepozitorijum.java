package com.projekat.UPIB.repositories;

import com.projekat.UPIB.models.Pregled;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PregledRepozitorijum extends JpaRepository<Pregled, Long> {
    List<Pregled> findByKlinikaIdKlinike (Long id);
    List<Pregled> findPregledsByLekarIdKorisnika (Long id);
    List<Pregled> findPregledsByPocetakTerminaIsOrPocetakTerminaIsAfterAndKlinikaIdKlinike(LocalDateTime date1, LocalDateTime date2, Long id);
}

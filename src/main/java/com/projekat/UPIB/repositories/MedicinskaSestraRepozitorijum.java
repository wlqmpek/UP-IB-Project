package com.projekat.UPIB.repositories;

import com.projekat.UPIB.models.MedicinskaSestra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicinskaSestraRepozitorijum  extends JpaRepository<MedicinskaSestra, Long> {
    MedicinskaSestra findMedicinskaSestraByEmailKorisnika(String email);
    List<MedicinskaSestra> findByKlinikaIdKlinike(Long id);
}

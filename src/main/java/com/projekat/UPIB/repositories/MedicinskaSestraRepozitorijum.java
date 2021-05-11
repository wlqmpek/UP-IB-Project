package com.projekat.UPIB.repositories;

import com.projekat.UPIB.models.MedicinskaSestra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicinskaSestraRepozitorijum  extends JpaRepository<MedicinskaSestra, Long> {
    MedicinskaSestra findMedicinskaSestraByEmailKorisnika(String email);
}

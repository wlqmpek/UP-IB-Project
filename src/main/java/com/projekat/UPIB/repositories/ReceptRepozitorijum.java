package com.projekat.UPIB.repositories;

import com.projekat.UPIB.models.Recept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceptRepozitorijum extends JpaRepository<Recept, Long> {
}

package com.projekat.UPIB.repositories;

import com.projekat.UPIB.models.Pacijent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacijentRepozitorijum extends JpaRepository<Pacijent, Long> {
}

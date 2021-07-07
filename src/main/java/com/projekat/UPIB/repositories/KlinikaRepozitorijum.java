package com.projekat.UPIB.repositories;

import com.projekat.UPIB.models.Klinika;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KlinikaRepozitorijum extends JpaRepository<Klinika, Long> {
}

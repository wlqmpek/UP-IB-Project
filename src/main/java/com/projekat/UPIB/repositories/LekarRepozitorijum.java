package com.projekat.UPIB.repositories;

import com.projekat.UPIB.models.Lekar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LekarRepozitorijum extends JpaRepository<Lekar, Long> {
    Lekar findLekarByEmailKorisnika(String email);
}

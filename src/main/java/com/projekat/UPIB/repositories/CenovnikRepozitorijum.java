package com.projekat.UPIB.repositories;

import com.projekat.UPIB.models.Cenovnik;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CenovnikRepozitorijum extends JpaRepository<Cenovnik, Long> {

    List<Cenovnik> findByKlinikaIdKlinike(Long id);
}

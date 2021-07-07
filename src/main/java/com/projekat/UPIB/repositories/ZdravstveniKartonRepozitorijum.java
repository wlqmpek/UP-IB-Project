package com.projekat.UPIB.repositories;

import com.projekat.UPIB.models.ZdravstveniKarton;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZdravstveniKartonRepozitorijum extends JpaRepository<ZdravstveniKarton, Long> {
}

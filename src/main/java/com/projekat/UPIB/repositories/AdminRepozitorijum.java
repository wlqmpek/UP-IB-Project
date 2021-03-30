package com.projekat.UPIB.repositories;

import com.projekat.UPIB.models.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepozitorijum  extends JpaRepository<Administrator, Long> {
}

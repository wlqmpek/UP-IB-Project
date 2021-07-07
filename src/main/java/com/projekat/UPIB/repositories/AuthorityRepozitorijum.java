package com.projekat.UPIB.repositories;

import com.projekat.UPIB.models.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepozitorijum extends JpaRepository<Authority, Long> {
    Authority findByImeAuthority(String name);
}

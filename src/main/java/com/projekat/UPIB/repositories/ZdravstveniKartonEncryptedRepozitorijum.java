package com.projekat.UPIB.repositories;

import com.projekat.UPIB.models.ZdravstveniKartonEncrypted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZdravstveniKartonEncryptedRepozitorijum extends JpaRepository<ZdravstveniKartonEncrypted, String> {
}

package com.projekat.UPIB.services;

import com.projekat.UPIB.models.Authority;

import java.util.List;

public interface IAuthorityService {

    List<Authority> findByIdAuthority(Long id);
    List<Authority> findByImeAuthority(String name);

}

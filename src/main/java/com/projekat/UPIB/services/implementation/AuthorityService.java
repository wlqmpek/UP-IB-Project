package com.projekat.UPIB.services.implementation;

import com.projekat.UPIB.models.Authority;
import com.projekat.UPIB.repositories.AuthorityRepozitorijum;
import com.projekat.UPIB.services.IAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorityService implements IAuthorityService {

    @Autowired
    private AuthorityRepozitorijum authorityRepozitorijum;

    @Override
    public List<Authority> findByIdAuthority(Long id) {
        Authority auth = this.authorityRepozitorijum.getOne(id);
        ArrayList<Authority> auths = new ArrayList<>();
        auths.add(auth);
        return auths;
    }

    @Override
    public List<Authority> findByImeAuthority(String name) {
        Authority auth = this.authorityRepozitorijum.findByImeAuthority(name);
        List<Authority> auths = new ArrayList<>();
        auths.add(auth);
        return auths;
    }

}

package com.projekat.UPIB.security.auth;

import com.projekat.UPIB.models.Korisnik;
import com.projekat.UPIB.services.implementation.KorisnikService;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private KorisnikService korisnikService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Korisnik user = korisnikService.findKorisnikByEmailKorisnika(email);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with email '%s'.", email));
        } else {
            return user;
        }
    }
}

package com.ccc.projects.lavoisier_api_v3.security;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ccc.projects.lavoisier_api_v3.models.Personal;
import com.ccc.projects.lavoisier_api_v3.repositories.PersonalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final PersonalRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Personal personal = repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("No user found"));
        return new User(personal.getEmail(), personal.getPassword(), List.of(new SimpleGrantedAuthority(personal.getRol().toString())));
    }
}

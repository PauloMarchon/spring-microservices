package com.paulomarchon.authserver.security.service;

import com.paulomarchon.authserver.user.AppUser;
import com.paulomarchon.authserver.user.AppUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AppUserRepository appUserRepository;

    public UserDetailsServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.selectAppUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("AppUser not found with email: "+ username));
        return UserDetailsImpl.build(appUser);
    }
}

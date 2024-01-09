package com.paulomarchon.authserver.security.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paulomarchon.authserver.user.AppUser;
import com.paulomarchon.authserver.user.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {
    private String id;
    private String username;
    private String email;
    @JsonIgnore
    private String password;
    private Role role;
    private boolean isEnable;

    public UserDetailsImpl(String id, String username, String email, String password, Role role, boolean isEnable) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isEnable = isEnable;
    }

    public static UserDetailsImpl build(AppUser appUser) {
        return new UserDetailsImpl(
                appUser.getId(),
                appUser.getUsername(),
                appUser.getEmail(),
                appUser.getPassword(),
                appUser.getRole(),
                appUser.isEnable()
        );
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnable;
    }
}

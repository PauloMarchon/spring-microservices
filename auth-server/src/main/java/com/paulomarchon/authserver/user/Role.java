package com.paulomarchon.authserver.user;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.paulomarchon.authserver.user.Permission.*;

public enum Role {

    ADMIN(
            Set.of(
                ADMIN_READ,
                ADMIN_UPDATE,
                ADMIN_DELETE,
                ADMIN_CREATE,
                MANAGER_READ,
                MANAGER_UPDATE,
                MANAGER_DELETE,
                MANAGER_CREATE
            )
    ),
    MANAGER(
            Set.of(
                MANAGER_READ,
                MANAGER_UPDATE,
                MANAGER_DELETE,
                MANAGER_CREATE
            )
    ),
    USER(Collections.emptySet());

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
}

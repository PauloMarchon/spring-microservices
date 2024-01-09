package com.paulomarchon.authserver.user;

import java.time.LocalDate;
import java.util.UUID;

public class AppUser {
    private final String id;
    private String username;
    private String email;
    private String password;
    private Role role;
    private final LocalDate createdAt;
    private boolean isEnable;


    public AppUser(String username, String email, String password) {
        this(
                UUID.randomUUID().toString(),
                username,
                email,
                password,
                Role.USER,
                LocalDate.now(),
                false
        );
    }

    public AppUser(String username, String email, String password, Role role) {
        this(
                UUID.randomUUID().toString(),
                username,
                email,
                password,
                role,
                LocalDate.now(),
                false
        );
    }

    public AppUser(String id, String username, String email, String password, Role role, LocalDate createdAt, boolean isEnable) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.createdAt = createdAt;
        this.isEnable = isEnable;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }
}

package com.paulomarchon.authserver.user;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository {
    List<AppUser> selectAllAppUsers();
    Optional<AppUser> selectAppUserById(String id);
    Optional<AppUser> selectAppUserByUsername(String username);
    Optional<AppUser> selectAppUserByEmail(String email);
    void registerAppUser(AppUser appUser);
    void deleteAppUserById(String appUserId);
    void updateAppUser(AppUser update);
    void activateAppUser(String appUserId);
    boolean existsAppUserById(String id);
    boolean existsAppUserWithUsername(String username);
    boolean existsAppUserWithEmail(String email);

}

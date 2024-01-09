package com.paulomarchon.authserver.user;

import com.paulomarchon.authserver.exception.DuplicateResourceException;
import com.paulomarchon.authserver.exception.ResourceNotFoundException;
import com.paulomarchon.authserver.user.payload.AppUserDto;
import com.paulomarchon.authserver.user.payload.AppUserRegistrationRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;
    private final AppUserDtoMapper appUserDtoMapper;
    private final PasswordEncoder passwordEncoder;

    public AppUserService(AppUserRepository appUserRepository, AppUserDtoMapper appUserDtoMapper, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appUserDtoMapper = appUserDtoMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<AppUserDto> getAllAppUsers() {
        return appUserRepository.selectAllAppUsers()
                .stream()
                .map(this.appUserDtoMapper)
                .collect(Collectors.toList());
    }

    public AppUserDto getAppUser(String appUserId) {
        return appUserRepository.selectAppUserById(appUserId)
                .map(this.appUserDtoMapper)
                .orElseThrow(() -> new ResourceNotFoundException("appuser with id [%s] not found".formatted(appUserId)));
    }

    public AppUserDto getAppUserByUsername(String username) {
        return appUserRepository.selectAppUserByUsername(username)
                .map(this.appUserDtoMapper)
                .orElseThrow(() -> new ResourceNotFoundException("appuser with username [%s] not found".formatted(username)));
    }

    public AppUserDto getAppUserByEmail(String email) {
        return appUserRepository.selectAppUserByEmail(email)
                .map(this.appUserDtoMapper)
                .orElseThrow(() -> new ResourceNotFoundException("appuser with email [%s] not found".formatted(email)));
    }

    public void registerAppUser(AppUserRegistrationRequest appUserRegistrationRequest) {
        String username = appUserRegistrationRequest.username();
        if (appUserRepository.existsAppUserWithUsername(username))
            throw new DuplicateResourceException("Username is already in use");

        String email = appUserRegistrationRequest.email();
        if (appUserRepository.existsAppUserWithEmail(email))
            throw new DuplicateResourceException("Email already registered");

        AppUser appUser = new AppUser(
                appUserRegistrationRequest.username(),
                appUserRegistrationRequest.email(),
                passwordEncoder.encode(appUserRegistrationRequest.password())
        );
        appUserRepository.registerAppUser(appUser);
    }

    public void deleteAppUser(String appUserId) {
        if (!appUserRepository.existsAppUserById(appUserId))
            throw new ResourceNotFoundException("appuser with id [%s] not found".formatted(appUserId));
        appUserRepository.deleteAppUserById(appUserId);
    }

    public void updateAppUser(){
        //TODO
    }

    public void activateAppUser(String appUserId) {
        //TODO
    }
}

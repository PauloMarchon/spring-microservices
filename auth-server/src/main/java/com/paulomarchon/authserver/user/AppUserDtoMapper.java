package com.paulomarchon.authserver.user;

import com.paulomarchon.authserver.user.payload.AppUserDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AppUserDtoMapper implements Function<AppUser, AppUserDto> {
    @Override
    public AppUserDto apply(AppUser appUser) {
        return new AppUserDto(
                appUser.getId(),
                appUser.getUsername(),
                appUser.getEmail(),
                appUser.getRole().name()
        );
    }
}

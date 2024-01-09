package com.paulomarchon.authserver.user.payload;

public record AppUserDto(
        String id,
        String username,
        String email,
        String role
) {
}

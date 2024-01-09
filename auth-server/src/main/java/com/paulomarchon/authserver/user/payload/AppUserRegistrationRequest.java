package com.paulomarchon.authserver.user.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AppUserRegistrationRequest(
        @NotBlank @Size(min = 3, max = 20) String username,
        @NotNull @Email String email,
        @NotBlank @Size(min = 6) String password
) {
}

package com.paulomarchon.authserver.user;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitAppUser {/*}  implements ApplicationRunner {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public InitAppUser(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        AppUser appUser = new AppUser("admin", "admin@admin.com", passwordEncoder.encode("admin"), Role.ADMIN);
        appUser.setEnable(true);
        appUserRepository.registerAppUser(appUser);
    }
*/
}

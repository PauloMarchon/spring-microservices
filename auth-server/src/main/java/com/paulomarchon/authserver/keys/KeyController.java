package com.paulomarchon.authserver.keys;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class KeyController {
    private final RsaKeyPairService keyPairService;
    private final RedisRsaKeyPairReactiveService redisRsaKeyService;

    public KeyController(RsaKeyPairService keyPairService, RedisRsaKeyPairReactiveService redisRsaKeyService) {
        this.keyPairService = keyPairService;
        this.redisRsaKeyService = redisRsaKeyService;
    }

    @PostMapping("/oauth2/new-jwks")
    public String generate() {
        RsaKeyPair newKeyPair = keyPairService.generateKeyPair(Instant.now());
        redisRsaKeyService.saveKeyPair(newKeyPair);
        return newKeyPair.getId();
    }
}

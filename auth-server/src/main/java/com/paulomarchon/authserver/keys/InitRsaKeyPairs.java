package com.paulomarchon.authserver.keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;

@Component
public class InitRsaKeyPairs implements ApplicationRunner {
    private final RsaKeyPairRedisRepository keyPairRepository;
    private final String kid;
    private final RSAPublicKey publicKey;
    private final RSAPrivateKey privateKey;

    public InitRsaKeyPairs(RsaKeyPairRedisRepository keyPairRepository,
                           @Value("${jwt.key.id}") String kid,
                           @Value("${jwt.key.public}")RSAPublicKey publicKey,
                           @Value("${jwt.key.private}")RSAPrivateKey privateKey) {
        this.keyPairRepository = keyPairRepository;
        this.kid = kid;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.keyPairRepository.save(
                new RsaKeyPair(this.kid, Instant.now(), publicKey, privateKey)
        );
    }
}

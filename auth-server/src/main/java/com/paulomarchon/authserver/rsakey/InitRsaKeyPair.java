package com.paulomarchon.authserver.rsakey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Component
public class InitRsaKeyPair {/*} implements ApplicationRunner {
    private final RsaKeyPairRepository keyPairRepository;
    private final RSAPublicKey publicKey;
    private final RSAPrivateKey privateKey;

    public InitRsaKeyPair(RsaKeyPairRepository keyPairRepository,
                          @Value("${jwt.key.public}") RSAPublicKey publicKey,
                          @Value("${jwt.key.private}") RSAPrivateKey privateKey) {
        this.keyPairRepository = keyPairRepository;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.keyPairRepository.save(new RsaKeyPair(publicKey, privateKey));
    }
    */
}

package com.paulomarchon.authserver.keys;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RsaKeyPairRedisService {
    private final RsaKeyPairRedisRepository keyPairRedisRepository;

    public RsaKeyPairRedisService(RsaKeyPairRedisRepository keyPairRedisRepository) {
        this.keyPairRedisRepository = keyPairRedisRepository;
    }

    public List<RsaKeyPair> findKeyPairs() {
        return null;
    }

    public void saveRsaKeyPair(RsaKeyPair rsaKeyPair) {
        keyPairRedisRepository.save(rsaKeyPair);
    }

    public RsaKeyPair getRsaKeyPair(String kid) {
        return null;
    }


}

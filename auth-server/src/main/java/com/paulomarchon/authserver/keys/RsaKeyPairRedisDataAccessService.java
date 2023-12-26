package com.paulomarchon.authserver.keys;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class RsaKeyPairRedisDataAccessService implements RsaKeyPairDao{
    @Override
    public Flux<RsaKeyPair> findKeyPairs() {
        return null;
    }

    @Override
    public Mono<Void> saveRsaKey(RsaKeyPair rsaKeyPair) {
        return null;
    }

    @Override
    public Mono<Void> deleteRsaKey(String id) {
        return null;
    }
}

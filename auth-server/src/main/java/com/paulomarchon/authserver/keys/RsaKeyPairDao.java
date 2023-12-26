package com.paulomarchon.authserver.keys;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RsaKeyPairDao {
    Flux<RsaKeyPair> findKeyPairs();
    Mono<Void> saveRsaKey(RsaKeyPair rsaKeyPair);
    Mono<Void> deleteRsaKey(String id);
}

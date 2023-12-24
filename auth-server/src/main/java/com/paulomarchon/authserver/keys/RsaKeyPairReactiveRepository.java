package com.paulomarchon.authserver.keys;

import reactor.core.publisher.Mono;

import java.util.List;

public interface RsaKeyPairReactiveRepository {
    List<RsaKeyPair> findKeyPairs();
    Mono<RsaKeyPair> getKeyPair(String kid);
    Mono<Boolean> saveKeyPair(RsaKeyPair rsaKeyPair);
    Mono<Boolean> existsForKey(String kid);
    Mono<Boolean> deleteKeyPair(String kid);
}

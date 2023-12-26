package com.paulomarchon.authserver.keys;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.List;

public interface RsaKeyPairReactiveRepository extends ReactiveCrudRepository {
    List<RsaKeyPair> findKeyPairs();
    Mono<RsaKeyPair> getKeyPair(String kid);
    Mono<Boolean> saveKeyPair(RsaKeyPair rsaKeyPair);
    Mono<Boolean> existsForKey(String kid);
    Mono<Boolean> deleteKeyPair(String kid);
}

package com.paulomarchon.authserver.keys;

import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public class RedisRsaKeyPairReactiveRepository {

    private final ReactiveRedisTemplate<String, RsaKeyPair> redisTemplate;

    public RedisRsaKeyPairReactiveRepository(ReactiveRedisTemplate<String, RsaKeyPair> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List<RsaKeyPair> findKeyPairs() {
        return null;
    }

    @Override
    public Mono<RsaKeyPair> getKeyPair(String kid) {
        return redisTemplate
                .opsForValue()
                .get(kid);
    }

    @Override
    public Mono<Boolean> saveKeyPair(RsaKeyPair rsaKeyPair) {
        return redisTemplate
                .opsForValue()
                .set(rsaKeyPair.getId(), rsaKeyPair);
    }

    @Override
    public Mono<Boolean> existsForKey(String kid) {
        return redisTemplate
                .hasKey(kid);
    }

    @Override
    public Mono<Boolean> deleteKeyPair(String kid) {
        return redisTemplate
                .opsForValue()
                .delete(kid);
    }
}

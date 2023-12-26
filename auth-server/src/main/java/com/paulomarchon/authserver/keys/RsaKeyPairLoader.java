package com.paulomarchon.authserver.keys;

import jakarta.annotation.PostConstruct;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class RsaKeyPairLoader {

/*
    @PostConstruct
    public void loadData(){
        RsaKeyPair rsaKeyPair = initRsaKeyPairs.createKeyPair();
        factory.getReactiveConnection().serverCommands().flushAll().thenMany(
                redisDataAccessService.saveRsaKeyPair(rsaKeyPair)
        ).subscribe();
    }

 */
}

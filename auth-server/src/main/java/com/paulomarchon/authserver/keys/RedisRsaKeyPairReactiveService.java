package com.paulomarchon.authserver.keys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class RedisRsaKeyPairReactiveService {
    private static final Logger logger = LoggerFactory.getLogger(RedisRsaKeyPairReactiveService.class);
    private final RedisRsaKeyPairReactiveRepository redisRsaKeyPairRepository;

    public RedisRsaKeyPairReactiveService(RedisRsaKeyPairReactiveRepository redisRsaKeyPairRepository) {
        this.redisRsaKeyPairRepository = redisRsaKeyPairRepository;
    }

    public Mono<RsaKeyPair> getKeyPair(String kid) {
        try {
            return redisRsaKeyPairRepository.getKeyPair(kid)
                    .doOnNext(response -> logger.info("Return KeyPair for kid: {}", kid));
        } catch (Exception ex) {
            logger.error("error when trying to search for Key with kid: {}", kid);
        }
        return Mono.empty();
    }

    public Mono<String> saveKeyPair(RsaKeyPair rsaKeyPair) {
        try {
            return redisRsaKeyPairRepository
                    .saveKeyPair(rsaKeyPair)
                    .flatMap(saved -> {
                        if (saved) {
                            logger.info("RsaKey save successfully {}", rsaKeyPair.getId());
                        } else {
                            logger.info("Error when trying to save {}", rsaKeyPair.getId());
                        }
                        return Mono.just(rsaKeyPair.getId());
                    });
        } catch (Exception ex) {
            logger.error("Erro on execution to save the key");
        }
        return Mono.just(rsaKeyPair.getId());
    }


    public Mono<Boolean> existsForKey(String kid) {
        try {
            return redisRsaKeyPairRepository.existsForKey(kid)
                    .doOnNext(exists -> {
                        if (exists) {
                            logger.info("RsaKey with the informed kid exists, kid: {}", kid);
                        } else {
                            logger.info("RsaKey with the informed kid not exists, kid: {}", kid);
                        }
                    });
        } catch (Exception ex) {
            logger.error("error when trying to check if RsaKey with informed Kid: {} exists", kid);
        }
        return Mono.just(false);
    }


    //public Mono<Boolean> deleteKeyPair(String kid) {}
}

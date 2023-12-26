package com.paulomarchon.authserver.keys;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RsaKeyPairRedisRepository extends CrudRepository<RsaKeyPair, String> {
    List<RsaKeyPair> findKeyPairs();
}

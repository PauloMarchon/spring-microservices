package com.paulomarchon.authserver.rsakey;

import java.util.List;

public interface RsaKeyPairRepository {
    List<RsaKeyPair> findKeyPairs();
    void save(RsaKeyPair rsaKeyPair);
    void delete(String id);
}

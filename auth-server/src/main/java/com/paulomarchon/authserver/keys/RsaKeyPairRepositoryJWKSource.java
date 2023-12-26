package com.paulomarchon.authserver.keys;

import com.nimbusds.jose.KeySourceException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import java.util.ArrayList;
import java.util.List;

public class RsaKeyPairRepositoryJWKSource implements JWKSource<SecurityContext>, OAuth2TokenCustomizer<JwtEncodingContext> {
  private final RsaKeyPairRedisRepository keyPairRepository;

    public RsaKeyPairRepositoryJWKSource(RsaKeyPairRedisRepository keyPairRepository) {
        this.keyPairRepository = keyPairRepository;
    }

    @Override
    public List<JWK> get(JWKSelector jwkSelector, SecurityContext context) throws KeySourceException {
        List<RsaKeyPair> keyPairs = (List<RsaKeyPair>) this.keyPairRepository.findKeyPairs();
        List<JWK> result = new ArrayList<>(keyPairs.size());

        for (RsaKeyPair keyPair : keyPairs) {

            RSAKey rsaKey = new RSAKey.Builder(keyPair.getPublicKey())
                    .privateKey(keyPair.getPrivateKey())
                    .keyID(keyPair.getId())
                    .build();
            if (jwkSelector.getMatcher().matches(rsaKey))
                result.add(rsaKey);
        }
        return result;
    }

    @Override
    public void customize(JwtEncodingContext context) {
        List<RsaKeyPair> keyPairs = this.keyPairRepository.findKeyPairs();
        String kid = keyPairs.get(0).getId();
        context.getJwsHeader().keyId(kid);
    }
}

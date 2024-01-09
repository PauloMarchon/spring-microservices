package com.paulomarchon.authserver.rsakey;

import com.nimbusds.jose.KeySourceException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RsaKeyPairRepositoryJWKSource implements JWKSource<SecurityContext>, OAuth2TokenCustomizer<JwtEncodingContext> {
    private final RsaKeyPairRepository keyPairRepositor;

    public RsaKeyPairRepositoryJWKSource(RsaKeyPairRepository keyPairRepositor) {
        this.keyPairRepositor = keyPairRepositor;
    }

    @Override
    public List<JWK> get(JWKSelector jwkSelector, SecurityContext context) throws KeySourceException {
        List<RsaKeyPair> keyPairs = this.keyPairRepositor.findKeyPairs();
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
        List<RsaKeyPair> keyPairs = this.keyPairRepositor.findKeyPairs();
        String kid = keyPairs.get(0).getId();
        context.getJwsHeader().keyId(kid);
    }
}

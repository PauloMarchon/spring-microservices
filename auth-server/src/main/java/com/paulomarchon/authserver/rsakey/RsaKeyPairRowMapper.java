package com.paulomarchon.authserver.rsakey;

import com.paulomarchon.authserver.rsakey.converter.RsaPrivateKeyConverter;
import com.paulomarchon.authserver.rsakey.converter.RsaPublicKeyConverter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;

@Component
public class RsaKeyPairRowMapper implements RowMapper<RsaKeyPair> {
    private final RsaPublicKeyConverter publicKeyConverter;
    private final RsaPrivateKeyConverter privateKeyConverter;

    public RsaKeyPairRowMapper(RsaPublicKeyConverter publicKeyConverter, RsaPrivateKeyConverter privateKeyConverter) {
        this.publicKeyConverter = publicKeyConverter;
        this.privateKeyConverter = privateKeyConverter;
    }

    @Override
    public RsaKeyPair mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            byte[] publicKeyBytes = rs.getString("public_key").getBytes();
            RSAPublicKey publicKey = this.publicKeyConverter.deserializeFromByteArray(publicKeyBytes);
            byte[] privateKeyBytes = rs.getString("private_key").getBytes();
            RSAPrivateKey privateKey = this.privateKeyConverter.deserializeFromByteArray(privateKeyBytes);
            Instant created = new Date(rs.getDate("created").getTime()).toInstant();

            return new RsaKeyPair(
                    rs.getString("id"),
                    created,
                    publicKey,
                    privateKey
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

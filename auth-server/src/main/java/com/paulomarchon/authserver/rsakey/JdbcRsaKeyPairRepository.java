package com.paulomarchon.authserver.rsakey;

import com.paulomarchon.authserver.rsakey.converter.RsaPrivateKeyConverter;
import com.paulomarchon.authserver.rsakey.converter.RsaPublicKeyConverter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Repository
public class JdbcRsaKeyPairRepository implements RsaKeyPairRepository{
    private final JdbcTemplate jdbcTemplate;
    private final RsaKeyPairRowMapper keyPairRowMapper;
    private final RsaPublicKeyConverter publicKeyConverter;
    private final RsaPrivateKeyConverter privateKeyConverter;

    public JdbcRsaKeyPairRepository(JdbcTemplate jdbcTemplate, RsaKeyPairRowMapper keyPairRowMapper, RsaPublicKeyConverter publicKeyConverter, RsaPrivateKeyConverter privateKeyConverter) {
        this.jdbcTemplate = jdbcTemplate;
        this.keyPairRowMapper = keyPairRowMapper;
        this.publicKeyConverter = publicKeyConverter;
        this.privateKeyConverter = privateKeyConverter;
    }

    @Override
    public List<RsaKeyPair> findKeyPairs() {
        var sql = """
                SELECT * FROM rsakeypair ORDER BY created DESC
                """;
        return jdbcTemplate.query(sql, this.keyPairRowMapper);
    }

    @Override
    public void save(RsaKeyPair rsaKeyPair) {
        var sql = """
                INSERT INTO rsakeypair (id, created, publickey, privatekey) VALUES (?, ?, ?, ?)
                """;
        try(var publicOutStream = new ByteArrayOutputStream(); var privateOutStream = new ByteArrayOutputStream()) {
            this.publicKeyConverter.serialize(rsaKeyPair.getPublicKey(), publicOutStream);
            this.privateKeyConverter.serialize(rsaKeyPair.getPrivateKey(), privateOutStream);

            this.jdbcTemplate.update(sql,
                    rsaKeyPair.getId(),
                    new Date(rsaKeyPair.getCreated().toEpochMilli()),
                    publicOutStream.toString(),
                    privateOutStream.toString()
            );
        } catch (IOException e) {
            throw new RuntimeException("Error when trying to save RsaKeyPair", e);
        }
    }

    @Override
    public void delete(String id) {
        var sql = """
                DELETE FROM rsakeypair WHERE id = ?
                """;
        this.jdbcTemplate.update(sql);
    }
}

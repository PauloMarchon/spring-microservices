package com.paulomarchon.authserver.keys;

import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufOutputStream;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

@Component
public class RsaKeyPairRedisSerializer implements RedisSerializer<RsaKeyPair> {
    @Override
    public byte[] serialize(RsaKeyPair value) throws SerializationException {
            try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                objectOutputStream.writeObject(value);
                objectOutputStream.flush();
                return byteArrayOutputStream.toByteArray();
            } catch (Exception ex) {
                throw new RuntimeException("Error serializing RsaKeyPair");
            }
    }

    @Override
    public RsaKeyPair deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null)
            return null;
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
            return (RsaKeyPair) objectInputStream.readObject();
        } catch (Exception ex) {
            throw new RuntimeException("Error deserializing RsaKeyPair");
        }
    }
}

package com.paulomarchon.authserver.redis;

import com.paulomarchon.authserver.keys.RsaKeyPair;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean
    ReactiveRedisTemplate<String, RsaKeyPair> redisOperations(ReactiveRedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer<RsaKeyPair> serializer = new Jackson2JsonRedisSerializer<>(RsaKeyPair.class);

        RedisSerializationContext.RedisSerializationContextBuilder<String, RsaKeyPair> builder =
                RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

        RedisSerializationContext<String, RsaKeyPair> context = builder.value(serializer).build();

        return new ReactiveRedisTemplate<>(factory, context);
    }
}

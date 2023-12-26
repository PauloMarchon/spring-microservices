package com.paulomarchon.authserver.redis;

import com.paulomarchon.authserver.keys.RsaKeyPair;
import com.paulomarchon.authserver.keys.RsaKeyPairRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@Configuration
class RedisConfig {
    private final RsaKeyPairRedisSerializer rsaKeyPairRedisSerializer;

    public RedisConfig(RsaKeyPairRedisSerializer rsaKeyPairRedisSerializer) {
        this.rsaKeyPairRedisSerializer = rsaKeyPairRedisSerializer;
    }
    @Bean
    LettuceConnectionFactory connectionFactory() {
        return new LettuceConnectionFactory();
    }

    @Bean
    ReactiveRedisTemplate<String, RsaKeyPair> ReactiveRedisTemplate(ReactiveRedisConnectionFactory connectionFactory) {
        Jackson2JsonRedisSerializer<RsaKeyPair> serializer = new Jackson2JsonRedisSerializer<>(RsaKeyPair.class);

        RedisSerializationContext.RedisSerializationContextBuilder<String, RsaKeyPair> builder =
                RedisSerializationContext.newSerializationContext(rsaKeyPairRedisSerializer);

        RedisSerializationContext<String, RsaKeyPair> context = builder.value(serializer).build();
        return new ReactiveRedisTemplate<>(connectionFactory, context);
    }
/*
    @Bean
    ReactiveRedisTemplate<String, RsaKeyPair> redisOperations(ReactiveRedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer<RsaKeyPair> serializer = new Jackson2JsonRedisSerializer<>(RsaKeyPair.class);

        RedisSerializationContext.RedisSerializationContextBuilder<String, RsaKeyPair> builder =
                RedisSerializationContext.newSerializationContext(rsaKeyPairRedisSerializer);

        RedisSerializationContext<String, RsaKeyPair> context = builder.value(serializer).build();

        return new ReactiveRedisTemplate<>(factory, context);
    }
    */
}


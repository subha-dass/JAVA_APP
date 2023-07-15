package com.red.rediswithspringboot.config;

import com.red.rediswithspringboot.Model.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Value("${redis.host.url}")
    String hostname;

    @Value("${redis.host.port}")
    Integer port;

    @Value("${redis.auth.password}")
    String password;

    @Bean
    public LettuceConnectionFactory getConnection(){
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(hostname, port);
        configuration.setPassword(password);

        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(configuration);

        return lettuceConnectionFactory;
    }

    @Bean
    public RedisTemplate<String,UserModel> getTemplate() {

        RedisTemplate<String, UserModel> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(getConnection());

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        return redisTemplate;
    }
}

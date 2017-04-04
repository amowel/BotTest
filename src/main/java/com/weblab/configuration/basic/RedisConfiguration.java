package com.weblab.configuration.basic;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.persistence.criteria.CriteriaBuilder;
import java.nio.charset.StandardCharsets;

@Configuration
public class RedisConfiguration extends CachingConfigurerSupport{
    @Bean
    public JedisConnectionFactory jedisConnectionFactory()
    {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName("127.0.0.1");
        jedisConnectionFactory.setPort(6379);
        return jedisConnectionFactory;
    }
    @Bean
    public RedisTemplate<String, Object> redisTemplate()
    {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setKeySerializer( new StringRedisSerializer() );
        redisTemplate.setHashValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
        redisTemplate.setValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
        redisTemplate.setExposeConnection(true);
        return redisTemplate;
    }
    @Bean
    public RedisCacheManager cacheManager()
    {
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate());
        redisCacheManager.setTransactionAware(true);
        redisCacheManager.setLoadRemoteCachesOnStartup(true);
        redisCacheManager.setUsePrefix(true);
        return redisCacheManager;
    }}
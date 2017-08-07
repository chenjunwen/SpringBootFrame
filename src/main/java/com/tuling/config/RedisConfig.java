package com.tuling.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {
    /**
     * 连接
     *
     * @param config
     * @param host
     * @param port
     * @param password
     * @return
     */
    @Bean(name = "jedis.pool")
    @Autowired
    public JedisPool jedisPool(@Qualifier("redis.pool.config") JedisPoolConfig config,
                               @Value("${spring.redis.host}") String host,
                               @Value("${spring.redis.timeout}") int timeout,
                               @Value("${spring.redis.port}") int port,
                               @Value("${spring.redis.password}") String password) {
        JedisPool jedisPool = new JedisPool(config, host, port, timeout, password);
        return jedisPool;
    }

    /**
     * 配置jedis数据库连接池
     *
     * @param maxIdle   最大空闲连接
     * @param maxActive 最大连接数
     * @param maxWait   最大等待时间
     * @param minIdle   最小连接数
     * @return
     */
    @Bean(name = "redis.pool.config")
    public JedisPoolConfig jedisPoolConfig(@Value("${spring.redis.pool.maxIdle}") int maxIdle,
                                           @Value("${spring.redis.pool.max-active}") int maxActive,
                                           @Value("${spring.redis.pool.max-wait}") int maxWait,
                                           @Value("${spring.redis.pool.min-idle}") int minIdle) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxActive);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWait);
        config.setMinIdle(minIdle);
        return config;
    }

}  
package com.brainstars.bullsandcows.configurations;

import com.brainstars.bullsandcows.models.Game;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfiguration {

  @Bean
  public RBucket<Game> rBucket() {
    RedissonClient client = Redisson.create();
    return client.getBucket("game");
  }
}

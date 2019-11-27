package com.webonise.todoapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.webonise.todoapp.model.Todo;

@EnableScheduling
@SpringBootApplication
public class TodoapiApplication {

  @Value("${spring.redis.host}")
  private String hostName;
  @Value("${spring.redis.password}")
  private String password;
  @Value("${spring.redis.port}")
  private int port;

  public static void main(String[] args) {
    SpringApplication.run(TodoapiApplication.class, args);
  }

  @Bean
  JedisConnectionFactory jedisConnectionFactory() {
    RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
    redisStandaloneConfiguration.setHostName(hostName);
    redisStandaloneConfiguration.setPort(port);
    redisStandaloneConfiguration.setPassword(password);
    return new JedisConnectionFactory(redisStandaloneConfiguration);
  }

  @Bean
  RedisTemplate<String, Todo> redisTemplate() {
    RedisTemplate<String, Todo> redisTemplate = new RedisTemplate<String, Todo>();
    redisTemplate.setConnectionFactory(jedisConnectionFactory());
    return redisTemplate;
  }
}

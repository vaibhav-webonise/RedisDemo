package com.webonise.todoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.webonise.todoapp.model.Todo;

@EnableScheduling
@SpringBootApplication
public class TodoapiApplication {

  public static void main(String[] args) {
    SpringApplication.run(TodoapiApplication.class, args);
  }

  @Bean
  JedisConnectionFactory jedisConnectionFactory() {
    return new JedisConnectionFactory();
  }

  @Bean
  RedisTemplate<String, Todo> redisTemplate() {
    RedisTemplate<String, Todo> redisTemplate = new RedisTemplate<String, Todo>();
    redisTemplate.setConnectionFactory(jedisConnectionFactory());
    return redisTemplate;
  }
}

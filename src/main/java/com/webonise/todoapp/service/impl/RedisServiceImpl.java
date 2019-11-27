package com.webonise.todoapp.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.webonise.todoapp.dao.TodoRepository;
import com.webonise.todoapp.model.Todo;
import com.webonise.todoapp.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {

  @Value("${redis.cache.key}")
  private String KEY;

  @Autowired
  private TodoRepository todoRepository;
  private RedisTemplate<String, Todo> redisTemplate;
  private HashOperations<String, Integer, Todo> hashOperations;

  public RedisServiceImpl(RedisTemplate<String, Todo> redisTemplate) {
    this.redisTemplate = redisTemplate;
    hashOperations = redisTemplate.opsForHash();
  }

  @Override
  public Map<Integer, Todo> getAllItems() {
    return hashOperations.entries(KEY);
  }

  @Override
  public Todo getItem(int todoId) {
    return (Todo) hashOperations.get(KEY, todoId);
  }

  @Override
  public void addItem(Todo todo) {
    hashOperations.put(KEY, todo.getId(), todo);
  }

  @Override
  public Boolean clearRedisCache() {
    return redisTemplate.delete(KEY);
  }

  @Override
  @Scheduled(fixedDelayString = "${redisupdate.execution.fixedDelay}", initialDelayString = "${redisupdate.execution.fixedDelay}")
  public void updateRedis() {
    if (clearRedisCache()) {
      List<Todo> todoList = todoRepository.findAll();
      if (!todoList.isEmpty()) {
        for (Todo todo : todoList) {
          addItem(todo);
        }
      }
    }
  }
}

package com.webonise.todoapp.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.webonise.todoapp.Exception.TodosNotExistException;
import com.webonise.todoapp.dao.TodoRepository;
import com.webonise.todoapp.model.Todo;
import com.webonise.todoapp.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {

  @Value("${redis.cache.key}")
  private String KEY;
  private static final long fixedDelay = 180000;
  private static final long initialDelay = 600000;

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
  public void clearRedisCache() {
    redisTemplate.execute(new RedisCallback<Void>() {
      @Override
      public Void doInRedis(RedisConnection connection) throws DataAccessException {
        connection.flushAll();
        return null;
      }
    });
  }

  @Override
  @Scheduled(fixedDelay = fixedDelay, initialDelay = initialDelay)
  public void updateRedis() {
    clearRedisCache();
    List<Todo> todoList = todoRepository.findAll();
    if (!todoList.isEmpty()) {
      for (Todo todo : todoList) {
        addItem(todo);
      }
    } else {
      throw new TodosNotExistException("No todos available");
    }
  }
}

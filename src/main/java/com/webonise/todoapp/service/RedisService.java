package com.webonise.todoapp.service;

import java.util.Map;
import com.webonise.todoapp.model.Todo;

public interface RedisService {

  /*
   * This service method returns all the key value pair available in the redis cache
   * @Param 
   * @return map with key and value pairs
   */
  public Map<Integer, Todo> getAllItems();
  
  /*
   * This method will return the specific value based on the provided key
   * @param key
   * @return value
   */
  public Todo getItem(int itemId);
  
  /*
   * This service method adds the new key-value pair in redis cache
   * @param value
   * @return 
   */
  public void addItem(Todo item);
  
  /*
   * This service method removes all the keys present in the cache
   */
  public void clearRedisCache();
  
  /*
   * This service method updates the redis cache
   */
  public void updateRedis();
}

package com.webonise.todoapp.dao;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.webonise.todoapp.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
  @Transactional
  int deleteTodoById(int id);

  @Modifying
  @Transactional
  @Query("UPDATE Todo t SET t.desc = :description WHERE t.id = :todoId")
  int updateTodo(@Param("todoId") int todoId, @Param("description") String description);
}

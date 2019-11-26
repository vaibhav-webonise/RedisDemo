package com.webonise.todoapp.Exception.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.webonise.todoapp.Exception.FailedToSaveEntityException;
import com.webonise.todoapp.Exception.TodoNotExistByGivenIdException;
import com.webonise.todoapp.Exception.TodosNotExistException;

@ControllerAdvice
public class ExceptionController {

  @ExceptionHandler(value = TodosNotExistException.class)
  public ResponseEntity<Object> exception(TodosNotExistException exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = TodoNotExistByGivenIdException.class)
  public ResponseEntity<Object> exception(TodoNotExistByGivenIdException exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = FailedToSaveEntityException.class)
  public ResponseEntity<Object> exception(FailedToSaveEntityException exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}

package com.webonise.todoapp.Exception;

public class TodosNotExistException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public TodosNotExistException(String message) {
    super(message);
  }
}

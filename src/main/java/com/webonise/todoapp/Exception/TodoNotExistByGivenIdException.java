package com.webonise.todoapp.Exception;

public class TodoNotExistByGivenIdException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public TodoNotExistByGivenIdException(String message) {
    super(message);
  }
}

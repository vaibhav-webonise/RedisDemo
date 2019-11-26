package com.webonise.todoapp.Exception;

public class FailedToSaveEntityException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public FailedToSaveEntityException(String message) {
    super(message);
  }
}

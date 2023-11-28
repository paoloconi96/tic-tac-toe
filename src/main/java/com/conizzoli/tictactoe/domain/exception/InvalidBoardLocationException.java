package com.conizzoli.tictactoe.domain.exception;

public class InvalidBoardLocationException extends Exception {
  public InvalidBoardLocationException() {
    super("The provided location must be included between numbers 0-8");
  }
}

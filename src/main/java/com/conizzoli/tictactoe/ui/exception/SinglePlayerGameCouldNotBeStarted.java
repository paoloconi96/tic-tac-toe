package com.conizzoli.tictactoe.ui.exception;

public class SinglePlayerGameCouldNotBeStarted extends RuntimeException {
  public SinglePlayerGameCouldNotBeStarted(Throwable cause) {
    super("Single player game could not be started.", cause);
  }
}

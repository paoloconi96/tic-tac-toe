package com.conizzoli.tictactoe.domain.exception;

import com.conizzoli.tictactoe.domain.model.SinglePlayerGameId;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public class SinglePlayerGameCouldNotBeFound extends Exception {
  public SinglePlayerGameCouldNotBeFound(
      @NonNull SinglePlayerGameId id, @Nullable Throwable cause) {
    super("Single player game with id %s could not be found.".formatted(id.toString()), cause);
  }
}

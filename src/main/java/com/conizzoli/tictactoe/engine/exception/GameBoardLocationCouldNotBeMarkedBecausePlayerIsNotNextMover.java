package com.conizzoli.tictactoe.engine.exception;

import com.conizzoli.tictactoe.engine.model.BoardLocation;
import com.conizzoli.tictactoe.engine.model.Player;

public class GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover extends Exception {
  public GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover(
      BoardLocation boardLocation, Player player) {
    super(
        String.format(
            "Game board location \"%s\" could not be marked because player \"%s\" is not next mover.",
            boardLocation.toString(), player.toSymbol()));
  }
}

package com.conizzoli.tictactoe.domain.exception;

import com.conizzoli.tictactoe.domain.model.BoardLocation;
import com.conizzoli.tictactoe.domain.model.Player;

public class GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover extends Exception {
  public GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover(
      BoardLocation boardLocation, Player player) {
    super(
        String.format(
            "Game board location \"%s\" could not be marked because player \"%s\" is not next mover.",
            boardLocation.toString(), player.toSymbol()));
  }
}

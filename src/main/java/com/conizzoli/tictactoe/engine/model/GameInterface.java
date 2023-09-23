package com.conizzoli.tictactoe.engine.model;

import com.conizzoli.tictactoe.engine.exception.BoardLocationAlreadyMarkedException;
import com.conizzoli.tictactoe.engine.exception.GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover;

import java.util.Optional;

public interface GameInterface {
  void mark(Player player, BoardLocation boardLocation)
      throws BoardLocationAlreadyMarkedException,
          GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover;

  Optional<Player> getWinner();

  boolean isInProgress();

  GameStatus getStatus();

  Player getNextMovePlayer();

  Optional<Player> getBoardLocationState(BoardLocation boardLocation);
}

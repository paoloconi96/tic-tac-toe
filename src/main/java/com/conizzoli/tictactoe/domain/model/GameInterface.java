package com.conizzoli.tictactoe.domain.model;

import com.conizzoli.tictactoe.domain.exception.BoardLocationAlreadyMarkedException;
import com.conizzoli.tictactoe.domain.exception.GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover;
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

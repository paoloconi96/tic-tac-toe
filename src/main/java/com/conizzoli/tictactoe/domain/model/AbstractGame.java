package com.conizzoli.tictactoe.domain.model;

import java.util.Optional;

abstract class AbstractGame implements GameInterface {
  protected final Board board;
  protected Player winner;
  protected GameStatus status = GameStatus.IN_PROGRESS;

  public AbstractGame() {
    this.board = new Board();
  }

  public Optional<Player> getWinner() {
    return Optional.ofNullable(winner);
  }

  public boolean isInProgress() {
    return this.status == GameStatus.IN_PROGRESS;
  }

  public GameStatus getStatus() {
    return status;
  }

  public Optional<Player> getBoardLocationState(BoardLocation boardLocation) {
    return this.board.getBoardLocationState(boardLocation);
  }
}

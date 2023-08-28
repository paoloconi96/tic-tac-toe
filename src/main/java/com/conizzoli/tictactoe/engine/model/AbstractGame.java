package com.conizzoli.tictactoe.engine.model;

abstract class AbstractGame implements GameInterface {
  protected final Board board;
  protected Player winner;
  protected GameStatus status = GameStatus.IN_PROGRESS;

  public AbstractGame() {
    this.board = new Board();
  }

  @Override
  public String toString() {
    return this.board.toString();
  }

  public Player getWinner() {
    return winner;
  }

  public boolean isInProgress() {
    return this.status == GameStatus.IN_PROGRESS;
  }

  public GameStatus getStatus() {
    return status;
  }

  public Player getBoardLocationState(BoardLocation boardLocation) {
    return this.board.getBoardLocationState(boardLocation);
  }
}

package com.conizzoli.tictactoe.engine.model;

import com.conizzoli.tictactoe.engine.exception.BoardLocationAlreadyMarkedException;
import com.conizzoli.tictactoe.engine.exception.GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover;

public class Game {
  private final Board board;
  private Player winner;
  private GameStatus status = GameStatus.IN_PROGRESS;
  private Player nextMovePlayer = Player.CROSS;

  public Game() {
    this.board = new Board();
  }

  public void mark(Player player, BoardLocation boardLocation)
      throws BoardLocationAlreadyMarkedException,
          GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover {
    if (player != this.nextMovePlayer) {
      throw new GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover(boardLocation, player);
    }

    this.board.mark(player, boardLocation);
    this.board
        .computeWinner()
        .ifPresent(
            winner -> {
              this.winner = winner;
              this.status = GameStatus.WON;
            });

    if (this.board.computeIsDraw()) {
      this.status = GameStatus.DRAW;
    }

    this.nextMovePlayer = this.nextMovePlayer == Player.CROSS ? Player.CIRCLE : Player.CROSS;
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

  public Player getNextMovePlayer() {
    return nextMovePlayer;
  }

  public Player getBoardLocationState(BoardLocation boardLocation) {
    return this.board.getBoardLocationState(boardLocation);
  }
}

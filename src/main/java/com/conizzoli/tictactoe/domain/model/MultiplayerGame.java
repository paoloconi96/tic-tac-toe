package com.conizzoli.tictactoe.domain.model;

import com.conizzoli.tictactoe.domain.exception.BoardLocationAlreadyMarkedException;
import com.conizzoli.tictactoe.domain.exception.GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover;

public class MultiplayerGame extends AbstractGame {
  private Player nextMovePlayer = Player.CROSS;

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

    if (this.nextMovePlayer == Player.CROSS) {
      this.nextMovePlayer = Player.CIRCLE;
    } else {
      this.nextMovePlayer = Player.CROSS;
    }
  }

  public Player getNextMovePlayer() {
    return nextMovePlayer;
  }
}

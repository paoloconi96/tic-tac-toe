package com.conizzoli.tictactoe.engine.model;

import com.conizzoli.tictactoe.engine.exception.BoardLocationAlreadyMarkedException;
import com.conizzoli.tictactoe.engine.exception.GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover;
import java.util.PriorityQueue;
import java.util.Random;

public class SinglePlayerGame extends AbstractGame {
  private final Random random = new Random();

  public void mark(Player player, BoardLocation boardLocation)
      throws BoardLocationAlreadyMarkedException,
          GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover {
    if (player != Player.CROSS) {
      throw new GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover(boardLocation, player);
    }

    this.board.mark(player, boardLocation);
    this.computerMark();

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
  }

  private void computerMark() throws BoardLocationAlreadyMarkedException {
    var possibleMoves = new PriorityQueue<Move>();

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        var boardLocation = new BoardLocation(i, j);

        if (!this.board.isBoardLocationMarked(boardLocation)) {
          this.board.mark(Player.CIRCLE, boardLocation);
          int moveVal = this.getBestMoveViaMiniMax(false, 0);
          this.board.removeMark(boardLocation);

          possibleMoves.add(new Move(moveVal, i, j));
        }
      }
    }

    var performBestMove = random.nextInt(100) <= 75;
    var move = possibleMoves.poll();
    if (!performBestMove && !possibleMoves.isEmpty()) {
      move = possibleMoves.poll();
    }

    if (move == null) {
      return;
    }

    this.board.mark(Player.CIRCLE, move.toBoardLocation());
  }

  private int getBestMoveViaMiniMax(boolean isComputerMove, int depth) {
    var winner = this.board.computeWinner();
    if (winner.isPresent()) {
      return winner.get() == Player.CROSS ? -10 + depth : 10 - depth;
    }

    if (this.board.computeIsDraw()) {
      return 0;
    }

    var best = isComputerMove ? -1000 : 1000;
    for (var i = 0; i < 3; i++) {
      for (var j = 0; j < 3; j++) {
        var boardLocation = new BoardLocation(i, j);

        if (this.board.isBoardLocationMarked(boardLocation)) {
          continue;
        }

        try {
          if (isComputerMove) {
            this.board.mark(Player.CIRCLE, boardLocation);
            best = Math.max(best, this.getBestMoveViaMiniMax(false, depth + 1));
          } else {
            this.board.mark(Player.CROSS, boardLocation);
            best = Math.min(best, this.getBestMoveViaMiniMax(true, depth + 1));
          }
        } catch (BoardLocationAlreadyMarkedException exception) {
          throw new RuntimeException(exception);
        }

        this.board.removeMark(boardLocation);
      }
    }

    return best;
  }

  public Player getNextMovePlayer() {
    return Player.CROSS;
  }
}

package com.conizzoli.tictactoe.engine.model;

import com.conizzoli.tictactoe.engine.exception.BoardLocationAlreadyMarkedException;
import com.conizzoli.tictactoe.engine.exception.GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover;
import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;

public class SinglePlayerGame extends AbstractGame {
  private final Random random;

  public SinglePlayerGame(Random random) {
    this.random = random;
  }

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
    var move =
        IntStream.rangeClosed(0, 8)
            .mapToObj(
                cell -> {
                  var boardLocation = new BoardLocation(cell / 3, cell % 3);

                  if (this.board.isBoardLocationMarked(boardLocation)) {
                    return null;
                  }

                  var clonedBoard = this.board.clone();
                  clonedBoard.nonBlockingMark(Player.CIRCLE, boardLocation);
                  int moveVal = this.getBestMoveViaMiniMax(clonedBoard, false, 0);

                  return new Move(moveVal, boardLocation);
                })
            .filter(Objects::nonNull)
            .sorted()
            .skip(random.nextInt(100) <= 75 ? 0 : 1)
            .findFirst();

    move.ifPresent(value -> this.board.nonBlockingMark(Player.CIRCLE, value.toBoardLocation()));
  }

  private int getBestMoveViaMiniMax(Board clonedBoard, boolean isComputerMove, int depth) {
    var winner = clonedBoard.computeWinner();
    if (winner.isPresent()) {
      return winner.get() == Player.CROSS ? -10 + depth : 10 - depth;
    }

    if (clonedBoard.computeIsDraw()) {
      return 0;
    }

    var best = isComputerMove ? -1000 : 1000;
    for (var i = 0; i < 9; i++) {
      var boardLocation = new BoardLocation(i / 3, i % 3);

      if (clonedBoard.isBoardLocationMarked(boardLocation)) {
        continue;
      }

      try {
        if (isComputerMove) {
          clonedBoard.mark(Player.CIRCLE, boardLocation);
          best = Math.max(best, this.getBestMoveViaMiniMax(clonedBoard, false, depth + 1));
        } else {
          clonedBoard.mark(Player.CROSS, boardLocation);
          best = Math.min(best, this.getBestMoveViaMiniMax(clonedBoard, true, depth + 1));
        }
      } catch (BoardLocationAlreadyMarkedException exception) {
        throw new RuntimeException(exception);
      }

      clonedBoard.removeMark(boardLocation);
    }

    return best;
  }

  public Player getNextMovePlayer() {
    return Player.CROSS;
  }
}

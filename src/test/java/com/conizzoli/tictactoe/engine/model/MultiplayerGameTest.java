package com.conizzoli.tictactoe.engine.model;

import com.conizzoli.tictactoe.engine.exception.BoardLocationAlreadyMarkedException;
import com.conizzoli.tictactoe.engine.exception.GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MultiplayerGameTest {
  @Test
  public void itGetsDefaultNextMovePlayer() {
    var game = new MultiplayerGame();
    Assertions.assertSame(Player.CROSS, game.getNextMovePlayer());
  }

  @Test
  public void itThrowsWhenNonNextPlayerMarks() {
    var game = new MultiplayerGame();
    Assertions.assertThrows(GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover.class, () -> {
      game.mark(Player.CIRCLE, new BoardLocation(0, 0));
    });
  }

  @Test
  public void itMarks() throws GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover, BoardLocationAlreadyMarkedException {
    var game = new MultiplayerGame();
    game.mark(Player.CROSS, new BoardLocation(0, 0));
  }

  @Test
  public void itMarksGameAsWon() throws GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover, BoardLocationAlreadyMarkedException {
    var game = new MultiplayerGame();

    game.mark(Player.CROSS, new BoardLocation(0, 0));
    game.mark(Player.CIRCLE, new BoardLocation(1, 0));
    game.mark(Player.CROSS, new BoardLocation(0, 1));
    game.mark(Player.CIRCLE, new BoardLocation(1, 1));
    game.mark(Player.CROSS, new BoardLocation(0, 2));

    var winner = game.getWinner();
    Assertions.assertTrue(winner.isPresent());
    Assertions.assertSame(Player.CROSS, winner.get());
    Assertions.assertSame(GameStatus.WON, game.getStatus());
  }

  @Test
  public void itMarksNextMovePlayer() throws GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover, BoardLocationAlreadyMarkedException {
    var game = new MultiplayerGame();
    game.mark(Player.CROSS, new BoardLocation(0, 0));

    Assertions.assertSame(Player.CIRCLE, game.getNextMovePlayer());
  }
}

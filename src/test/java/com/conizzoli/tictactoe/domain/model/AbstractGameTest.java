package com.conizzoli.tictactoe.domain.model;

import com.conizzoli.tictactoe.domain.exception.BoardLocationAlreadyMarkedException;
import com.conizzoli.tictactoe.domain.exception.GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AbstractGameTest {
  private class ConcreteGame extends AbstractGame {
    @Override
    public void mark(Player player, BoardLocation boardLocation) {}

    @Override
    public Player getNextMovePlayer() {
      return null;
    }

    public void setWinner(Player winner) {
      this.winner = winner;
    }
  }
  
  @Test
  public void itGetsWinner() throws GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover, BoardLocationAlreadyMarkedException {
    var game = new ConcreteGame();

    Assertions.assertFalse(game.getWinner().isPresent());
    game.setWinner(Player.CROSS);

    var winner = game.getWinner();
    Assertions.assertTrue(winner.isPresent());
    Assertions.assertSame(Player.CROSS, winner.get());
  }
}

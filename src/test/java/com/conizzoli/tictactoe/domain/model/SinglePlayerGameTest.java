package com.conizzoli.tictactoe.domain.model;

import com.conizzoli.tictactoe.domain.exception.BoardLocationAlreadyMarkedException;
import com.conizzoli.tictactoe.domain.exception.GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover;
import java.util.Optional;
import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SinglePlayerGameTest {
  @Mock
  private Random randomMock;

  @Test
  public void itAlwaysGetCrossAsNextMovePlayer() throws GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover, BoardLocationAlreadyMarkedException {
    var game = new SinglePlayerGame(new Random());
    Assertions.assertSame(Player.CROSS, game.getNextMovePlayer());
    game.mark(Player.CROSS, new BoardLocation(0, 0));
    Assertions.assertSame(Player.CROSS, game.getNextMovePlayer());
  }

  @Test
  public void itMarksCorrectlyForComputerWith100PercentPrecision() throws GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover, BoardLocationAlreadyMarkedException {
    Mockito.when(this.randomMock.nextInt(100))
      .thenReturn(50);
    var game = new SinglePlayerGame(this.randomMock);

    game.mark(Player.CROSS, new BoardLocation(1, 1));
    Assertions.assertEquals(Optional.of(Player.CIRCLE), game.getBoardLocationState(new BoardLocation(0, 0)));

    game.mark(Player.CROSS, new BoardLocation(0, 2));
    Assertions.assertEquals(Optional.of(Player.CIRCLE), game.getBoardLocationState(new BoardLocation(2, 0)));

    game.mark(Player.CROSS, new BoardLocation(1, 0));
    Assertions.assertEquals(Optional.of(Player.CIRCLE), game.getBoardLocationState(new BoardLocation(1, 2)));

    game.mark(Player.CROSS, new BoardLocation(0, 1));
    Assertions.assertEquals(Optional.of(Player.CIRCLE), game.getBoardLocationState(new BoardLocation(2, 1)));

    game.mark(Player.CROSS, new BoardLocation(2, 2));

    Assertions.assertSame(GameStatus.DRAW, game.getStatus());
  }

  @Test
  public void itMarksCorrectlyForComputerWith75PercentPrecision() throws GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover, BoardLocationAlreadyMarkedException {
    Mockito.when(this.randomMock.nextInt(100))
      .thenReturn(100);
    var game = new SinglePlayerGame(this.randomMock);

    game.mark(Player.CROSS, new BoardLocation(1, 1));
    Assertions.assertEquals(Optional.of(Player.CIRCLE), game.getBoardLocationState(new BoardLocation(0, 2)));

    game.mark(Player.CROSS, new BoardLocation(0, 0));
    Assertions.assertEquals(Optional.of(Player.CIRCLE), game.getBoardLocationState(new BoardLocation(0, 1)));

    game.mark(Player.CROSS, new BoardLocation(1, 0));
    Assertions.assertEquals(Optional.of(Player.CIRCLE), game.getBoardLocationState(new BoardLocation(2, 0)));

    game.mark(Player.CROSS, new BoardLocation(2, 1));
    Assertions.assertEquals(Optional.of(Player.CIRCLE), game.getBoardLocationState(new BoardLocation(2, 2)));

    game.mark(Player.CROSS, new BoardLocation(1, 2));

    Assertions.assertEquals(Optional.of(Player.CROSS), game.getWinner());
  }

  @Test
  public void itThrowsWhenTryingToMarkAsCircle() {
    var game = new SinglePlayerGame(new Random());
    Assertions.assertThrows(
      GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover.class,
      () -> game.mark(Player.CIRCLE, new BoardLocation(0, 0))
    );
  }
}

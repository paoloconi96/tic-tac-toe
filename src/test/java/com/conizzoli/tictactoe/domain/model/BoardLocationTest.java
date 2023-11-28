package com.conizzoli.tictactoe.domain.model;

import com.conizzoli.tictactoe.domain.exception.InvalidBoardLocationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardLocationTest {
  @Test
  public void itCreatesFromString() throws InvalidBoardLocationException {
    var boardLocation = BoardLocation.createFromString("5");

    Assertions.assertEquals(2, boardLocation.row());
    Assertions.assertEquals(1, boardLocation.column());
  }

  @Test
  public void itThrowsWhenStringIsInvalid() {
    Assertions.assertThrows(InvalidBoardLocationException.class, () -> {
      BoardLocation.createFromString(null);
    });
  }

  @Test
  public void itThrowsWhenNumberIsOutOfRange() {
    Assertions.assertThrows(InvalidBoardLocationException.class, () -> {
      BoardLocation.createFromString("10");
    });
  }
}

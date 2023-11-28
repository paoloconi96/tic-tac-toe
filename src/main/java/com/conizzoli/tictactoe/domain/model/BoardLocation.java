package com.conizzoli.tictactoe.domain.model;

import com.conizzoli.tictactoe.domain.exception.InvalidBoardLocationException;

public record BoardLocation(int row, int column) {
  public static BoardLocation createFromString(String baseNineLocation)
      throws InvalidBoardLocationException {
    int baseNineLocationInt;
    try {
      baseNineLocationInt = Integer.parseInt(baseNineLocation);
    } catch (NumberFormatException exception) {
      throw new InvalidBoardLocationException();
    }

    if (baseNineLocationInt > 8) {
      throw new InvalidBoardLocationException();
    }

    return new BoardLocation(baseNineLocationInt % 3, baseNineLocationInt / 3);
  }
}

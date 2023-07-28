package com.conizzoli.tictactoe.engine.model;

import com.conizzoli.tictactoe.engine.exception.BoardLocationAlreadyMarkedException;
import java.util.Optional;

class Board {
  private final Player[][] state = new Player[3][3];

  void mark(Player value, BoardLocation boardLocation) throws BoardLocationAlreadyMarkedException {
    var boardLocationPlayer = this.state[boardLocation.getY()][boardLocation.getX()];
    if (boardLocationPlayer != null) {
      throw new BoardLocationAlreadyMarkedException(boardLocationPlayer);
    }

    this.state[boardLocation.getY()][boardLocation.getX()] = value;
  }

  public Player getBoardLocationState(BoardLocation boardLocation) {
    return this.state[boardLocation.getY()][boardLocation.getX()];
  }

  Optional<Player> computeWinner() {
    // Winner by row
    outerLoop:
    for (Player[] row : this.state) {
      if (row[0] == null) {
        continue;
      }

      var potentialWinner = row[0];
      for (Player cell : row) {
        if (cell != potentialWinner) {
          continue outerLoop;
        }
      }

      return Optional.of(potentialWinner);
    }

    // Winner by column
    outerLoop:
    for (var i = 0; i < 3; i++) {
      if (this.state[0][i] == null) {
        continue;
      }
      var potentialWinner = this.state[0][i];

      for (var j = 0; j < 3; j++) {
        if (this.state[j][i] != potentialWinner) {
          continue outerLoop;
        }
      }

      return Optional.of(potentialWinner);
    }

    // Winner by diagonal
    var potentialWinner = this.state[1][1];
    if (potentialWinner != null) {
      if (this.state[0][0] == potentialWinner && this.state[2][2] == potentialWinner) {
        return Optional.of(potentialWinner);
      }

      if (this.state[2][0] == potentialWinner && this.state[0][2] == potentialWinner) {
        return Optional.of(potentialWinner);
      }
    }

    return Optional.empty();
  }

  boolean computeIsDraw() {
    for (Player[] row : this.state) {
      for (Player cell : row) {
        if (cell == null) {
          return false;
        }
      }
    }

    return true;
  }
}

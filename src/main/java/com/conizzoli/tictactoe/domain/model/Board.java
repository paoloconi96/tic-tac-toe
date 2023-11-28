package com.conizzoli.tictactoe.domain.model;

import com.conizzoli.tictactoe.domain.exception.BoardLocationAlreadyMarkedException;
import java.util.Arrays;
import java.util.Optional;

class Board implements Cloneable {
  private final Player[][] state;

  Board() {
     this(new Player[3][3]);
  }

  private Board(Player[][] state) {
    this.state = state;
  }

  void mark(Player value, BoardLocation boardLocation) throws BoardLocationAlreadyMarkedException {
    var boardLocationPlayer = this.state[boardLocation.column()][boardLocation.row()];
    if (boardLocationPlayer != null) {
      throw new BoardLocationAlreadyMarkedException(boardLocationPlayer);
    }

    this.state[boardLocation.column()][boardLocation.row()] = value;
  }

  void nonBlockingMark(Player player, BoardLocation boardLocation) {
    try {
      this.mark(player, boardLocation);
    } catch (BoardLocationAlreadyMarkedException exception) {
      throw new RuntimeException(exception);
    }
  }

  void removeMark(BoardLocation boardLocation) {
    this.state[boardLocation.column()][boardLocation.row()] = null;
  }

  public Optional<Player> getBoardLocationState(BoardLocation boardLocation) {
    return Optional.ofNullable(this.state[boardLocation.column()][boardLocation.row()]);
  }

  public boolean isBoardLocationMarked(BoardLocation boardLocation) {
    return this.state[boardLocation.column()][boardLocation.row()] != null;
  }

  Optional<Player> computeWinner() {
    // Winner by row
    for (Player[] row : this.state) {
      if (row[0] != null && row[0] == row[1] && row[1] == row[2]) {
        return Optional.of(row[0]);
      }
    }

    // Winner by column
    for (var i = 0; i < 3; i++) {
      if (this.state[0][i] != null && this.state[0][i] == this.state[1][i] && this.state[1][i] == this.state[2][i]) {
        return Optional.of(this.state[0][i]);
      }
    }

    // Winner by diagonal
    if (this.state[1][1] != null && this.state[0][0] == this.state[1][1] && this.state[1][1] == this.state[2][2]) {
      return Optional.of(this.state[1][1]);

    }
    if (this.state[1][1] != null && this.state[2][0] == this.state[1][1] && this.state[1][1] == this.state[0][2]) {
      return Optional.of(this.state[1][1]);
    }

    return Optional.empty();
  }

  boolean computeIsDraw() {
    if (this.computeWinner().isPresent()) {
      return false;
    }
    
    for (Player[] row : this.state) {
      for (Player cell : row) {
        if (cell == null) {
          return false;
        }
      }
    }

    return true;
  }

  @Override
  protected Board clone() {
    var stateClone = Arrays.stream(this.state)
      .map(Player[]::clone)
      .toArray(Player[][]::new);

    return new Board(stateClone);
  }
}

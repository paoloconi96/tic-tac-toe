package com.conizzoli.tictactoe.domain.model;

record Move(int value, int row, int column) implements Comparable<Move> {
  public Move(int value, BoardLocation boardLocation) {
    this(value, boardLocation.row(), boardLocation.column());
  }

  @Override
  public int compareTo(Move compareMove) {
    return this.value - compareMove.value;
  }

  public BoardLocation toBoardLocation() {
    return new BoardLocation(this.row, this.column);
  }
}

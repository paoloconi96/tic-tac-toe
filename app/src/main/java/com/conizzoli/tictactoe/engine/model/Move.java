package com.conizzoli.tictactoe.engine.model;

record Move(int value, int row, int column) implements Comparable<Move> {
  @Override
  public int compareTo(Move compareMove) {
    return compareMove.value - value;
  }

  public BoardLocation toBoardLocation() {
    return new BoardLocation(this.row, this.column);
  }
}

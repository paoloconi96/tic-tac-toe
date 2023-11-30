package com.conizzoli.tictactoe.domain.exception;

import com.conizzoli.tictactoe.domain.model.Player;

public class BoardLocationAlreadyMarkedException extends Exception {
  private final Player player;

  public BoardLocationAlreadyMarkedException(Player player) {
    super("The provided location has been already marked by " + player.toSymbol());
    this.player = player;
  }

  public Player getPlayer() {
    return this.player;
  }
}

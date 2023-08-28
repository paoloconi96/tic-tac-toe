package com.conizzoli.tictactoe.engine.exception;

import com.conizzoli.tictactoe.engine.model.Player;

public class BoardLocationAlreadyMarkedException extends Exception {
  private final Player player;

  public BoardLocationAlreadyMarkedException(Player player) {
    super("The provided location has been already marked by " + player.toSymbol());
    this.player = player;
  }

  public Player getPlayer() {
    return player;
  }
}

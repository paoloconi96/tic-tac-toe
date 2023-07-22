package com.conizzoli.tictactoe.engine;

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

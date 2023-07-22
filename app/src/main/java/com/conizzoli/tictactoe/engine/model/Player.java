package com.conizzoli.tictactoe.engine.model;

public enum Player {
    CROSS('X'), CIRCLE('O');

    private final char symbol;

    Player(char symbol) {
        this.symbol = symbol;
    }

    public String toSymbol() {
        return String.valueOf(this.symbol);
    }
}

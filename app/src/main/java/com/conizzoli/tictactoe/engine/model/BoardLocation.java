package com.conizzoli.tictactoe.engine.model;

import com.conizzoli.tictactoe.engine.exception.InvalidBoardLocationException;

public class BoardLocation {
    private final int x;
    private final int y;

    public BoardLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static BoardLocation createFromString(String baseNineLocation) throws InvalidBoardLocationException {
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

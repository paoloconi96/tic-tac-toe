package com.conizzoli.tictactoe.engine;

public class BoardLocation {
    private final byte x;
    private final byte y;

    private BoardLocation(byte x, byte y) {
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

        return new BoardLocation((byte) (baseNineLocationInt % 3), (byte) (baseNineLocationInt / 3));
    }

    public byte getX() {
        return x;
    }

    public byte getY() {
        return y;
    }
}

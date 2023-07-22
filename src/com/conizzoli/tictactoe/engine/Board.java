package com.conizzoli.tictactoe.engine;

import java.util.Optional;

class Board {
    private final Player[][] state = new Player[3][3];

    public void mark(Player value, BoardLocation boardLocation) throws BoardLocationAlreadyMarkedException {
        if (this.state[boardLocation.getY()][boardLocation.getX()] != null) {
            throw new BoardLocationAlreadyMarkedException(value);
        }

        this.state[boardLocation.getY()][boardLocation.getX()] = value;
    }

    @Override
    public String toString() {
        var stringBuilder = new StringBuilder();

        for (Player[] row : this.state) {
            for (Player cell : row) {
                stringBuilder.append(cell != null ? cell.toSymbol() : '-');
                stringBuilder.append(' ');
            }

            stringBuilder.append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }
    public String toPlayableString() {
        var stringBuilder = new StringBuilder();

        int position = 0;
        for (Player[] row : this.state) {
            for (Player cell : row) {
                stringBuilder.append(cell != null ? cell.toSymbol() : position);
                stringBuilder.append(' ');
                position++;
            }

            stringBuilder.append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }

    public Optional<Player> computeWinner(Player player) {
        rowLoop:
        for (Player[] row : this.state) {
            for (Player cell : row) {
                if (!player.equals(cell)) {
                    continue rowLoop;
                }
            }

            return Optional.of(player);
        }

        return Optional.empty();
    }
}

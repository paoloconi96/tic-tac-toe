package com.conizzoli.tictactoe.engine;

public class Game {
    private final Board board;
    private Player winner;
    private GameStatus status = GameStatus.IN_PROGRESS;
    private Player nextMovePlayer = Player.CROSS;

    public Game() {
        this.board = new Board();
    }

    public boolean mark(Player value, BoardLocation boardLocation) throws BoardLocationAlreadyMarkedException {
        if (value != this.nextMovePlayer) {
            throw new IllegalArgumentException("Wrong player");
        }

        this.board.mark(value, boardLocation);
        this.board.computeWinner(value)
                .ifPresent(winner -> this.winner = winner);

        return this.winner != null;
    }

    @Override
    public String toString() {
        return this.board.toString();
    }

    public String toPlayableString() {
        return this.board.toPlayableString();
    }

    public Player getWinner() {
        return winner;
    }

    public boolean isFinished() {
        return this.status == GameStatus.FINISHED;
    }

    public boolean isInProgress() {
        return this.status == GameStatus.IN_PROGRESS;
    }

    public Player getNextMovePlayer() {
        return nextMovePlayer;
    }
}

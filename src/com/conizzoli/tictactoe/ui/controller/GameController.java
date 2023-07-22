package com.conizzoli.tictactoe.ui.controller;

import com.conizzoli.tictactoe.engine.BoardLocation;
import com.conizzoli.tictactoe.engine.BoardLocationAlreadyMarkedException;
import com.conizzoli.tictactoe.engine.Game;
import com.conizzoli.tictactoe.engine.InvalidBoardLocationException;
import com.conizzoli.tictactoe.ui.service.PrinterService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class GameController {
    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private final PrinterService printerService = new PrinterService();
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("TicTacToeBundle");

    public void play() throws IOException {
        var game = new Game();

        while (game.isInProgress()) {
            this.requireMove(game);
        }

        this.printerService.printWinner(game);
    }

    private void requireMove(Game game) throws IOException {
        var player = game.getNextMovePlayer();

        this.printerService.printBoard(game);

        System.out.print(player.toSymbol() + " move: ");
        String move = this.bufferedReader.readLine();
        System.out.println();

        BoardLocation boardLocation;
        try {
            boardLocation = BoardLocation.createFromString(move);
        } catch (InvalidBoardLocationException exception) {
            System.out.println(resourceBundle.getString("invalidBoardLocationErrorMessage") + System.lineSeparator());
            return;
        }

        try {
            game.mark(player, boardLocation);
        } catch (BoardLocationAlreadyMarkedException exception) {
            String errorMessage = resourceBundle.getString("boardLocationAlreadyMarkedErrorMessage") +
                    " " +
                    exception.getPlayer().toSymbol() +
                    System.lineSeparator();
            System.out.println(errorMessage);
        }
    }
}

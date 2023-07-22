package com.conizzoli.tictactoe.ui.controller;

import com.conizzoli.tictactoe.engine.exception.GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover;
import com.conizzoli.tictactoe.engine.model.BoardLocation;
import com.conizzoli.tictactoe.engine.exception.BoardLocationAlreadyMarkedException;
import com.conizzoli.tictactoe.engine.model.Game;
import com.conizzoli.tictactoe.engine.exception.InvalidBoardLocationException;
import com.conizzoli.tictactoe.engine.service.PrinterService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.ResourceBundle;

public class GameController {
    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private final PrinterService printerService = new PrinterService();
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("TicTacToeBundle");

    public void play() throws IOException, GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover {
        var game = new Game();

        while (game.isInProgress()) {
            this.requireMove(game);
        }

        this.printerService.printWinner(game);
    }

    private void requireMove(Game game) throws IOException, GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover {
        var player = game.getNextMovePlayer();

        this.printerService.printBoard(game);

        System.out.print(MessageFormat.format(resourceBundle.getString("game.requireMoveMessage"), player.toSymbol()));
        String move = this.bufferedReader.readLine();
        System.out.println();

        BoardLocation boardLocation;
        try {
            boardLocation = BoardLocation.createFromString(move);
        } catch (InvalidBoardLocationException exception) {
            System.out.println(resourceBundle.getString("boardLocation.invalidMessage"));
            System.out.println();
            return;
        }

        try {
            game.mark(player, boardLocation);
        } catch (BoardLocationAlreadyMarkedException exception) {
            System.out.println(MessageFormat.format(
                resourceBundle.getString("boardLocation.alreadyMarkedMessage"),
                exception.getPlayer()
                    .toSymbol()
            ));
        }
    }
}

package com.conizzoli.tictactoe.ui.controller;

import com.conizzoli.tictactoe.engine.exception.BoardLocationAlreadyMarkedException;
import com.conizzoli.tictactoe.engine.exception.GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover;
import com.conizzoli.tictactoe.engine.exception.InvalidBoardLocationException;
import com.conizzoli.tictactoe.engine.model.BoardLocation;
import com.conizzoli.tictactoe.engine.model.Game;
import com.conizzoli.tictactoe.engine.service.PlayerService;
import com.conizzoli.tictactoe.engine.service.PrinterService;
import com.google.inject.Inject;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ResourceBundle;

public class GameController {
    private final PrinterService printerService;
    private final PlayerService playerService;
    private final BufferedReader bufferedReader;
    private final ResourceBundle resourceBundle;

    @Inject
    public GameController(PrinterService printerService, PlayerService playerService, BufferedReader bufferedReader, ResourceBundle resourceBundle) {
        this.printerService = printerService;
        this.playerService = playerService;
        this.bufferedReader = bufferedReader;
        this.resourceBundle = resourceBundle;
    }

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

        System.out.print(MessageFormat.format(
            resourceBundle.getString("game.requireMoveMessage"),
            playerService.getPlayerName(player)
        ));
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

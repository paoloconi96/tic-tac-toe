package com.conizzoli.tictactoe.engine.service;

import com.conizzoli.tictactoe.engine.model.BoardLocation;
import com.conizzoli.tictactoe.engine.model.Game;

import java.util.ResourceBundle;

public class PrinterService {
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("TicTacToeBundle");

    public void printWinner(Game game) {
        System.out.println(resourceBundle.getString("game.winnerMessage") + " " + game.getWinner().toSymbol() + "!!!");
    }

    public void printBoard(Game game) {
        var stringBuilder = new StringBuilder();
        for (var i = 0; i < 3; i++) {
            for (var j = 0; j < 3; j++) {
                var state = game.getBoardLocationState(new BoardLocation(j, i));

                stringBuilder.append(state != null ? state.toSymbol() : j + (i * 3));
                stringBuilder.append(' ');
            }

            stringBuilder.append(System.lineSeparator());
        }

        System.out.println(stringBuilder);
    }
}

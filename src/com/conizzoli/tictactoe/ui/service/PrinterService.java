package com.conizzoli.tictactoe.ui.service;

import com.conizzoli.tictactoe.engine.Game;

import java.util.ResourceBundle;

public class PrinterService {
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("TicTacToeBundle");

    public void printWinner(Game game) {
        System.out.println(resourceBundle.getString("winnerMessage") + " " + game.getWinner().toSymbol() + "!!!");
    }

    public void printBoard(Game game) {
        // TODO Perform this action here not in board model
        System.out.println(game.toPlayableString());
    }
}

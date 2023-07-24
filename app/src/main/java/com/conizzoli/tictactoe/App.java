package com.conizzoli.tictactoe;

import com.conizzoli.tictactoe.engine.exception.GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover;
import com.conizzoli.tictactoe.ui.controller.MainController;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover {
        var controller = new MainController();
        controller.invoke();
    }
}

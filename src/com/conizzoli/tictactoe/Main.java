package com.conizzoli.tictactoe;

import com.conizzoli.tictactoe.ui.controller.GameController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        var controller = new GameController();
        controller.play();
    }
}

package com.conizzoli.tictactoe.ui.controller;

import com.conizzoli.tictactoe.engine.exception.GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover;
import com.conizzoli.tictactoe.menu.MenuAction;
import com.google.inject.Inject;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController {
    private final BufferedReader bufferedReader;
    private final ResourceBundle resourceBundle;
    private final GameController gameController;
    private final HashMap<String, MenuAction> menuActionHashMap = new HashMap<>();
    {
        menuActionHashMap.put("1", MenuAction.NEW_GAME);
        menuActionHashMap.put("2", MenuAction.RESUME_GAME);
        menuActionHashMap.put("3", MenuAction.ASSIGN_PLAYER_NAMES);
        menuActionHashMap.put("0", MenuAction.EXIT);
    }

    @Inject
    public MainController(BufferedReader bufferedReader, ResourceBundle resourceBundle, GameController gameController) {
        this.bufferedReader = bufferedReader;
        this.resourceBundle = resourceBundle;
        this.gameController = gameController;
    }

    public void invoke() throws GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover, IOException {
        System.out.println();
        System.out.println(resourceBundle.getString("menu.description"));

        var numericSelection = this.bufferedReader.readLine();
        var menuSelection = this.menuActionHashMap.get(numericSelection);

        if (menuSelection == null) {
            System.out.println(resourceBundle.getString("system.invalidSelection"));
        } else {
            switch (Objects.requireNonNull(menuSelection)) {
                case ASSIGN_PLAYER_NAMES, RESUME_GAME ->
                        System.out.println(resourceBundle.getString("system.notImplemented"));
                case NEW_GAME -> this.gameController.play();
                case EXIT -> {
                    return;
                }
            }
        }

        this.invoke();
    }
}

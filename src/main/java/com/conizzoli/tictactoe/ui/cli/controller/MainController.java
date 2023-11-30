package com.conizzoli.tictactoe.ui.cli.controller;

import com.conizzoli.tictactoe.domain.exception.GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover;
import com.conizzoli.tictactoe.ui.cli.model.MenuAction;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ResourceBundle;

public class MainController {
  private final BufferedReader bufferedReader;
  private final ResourceBundle resourceBundle;
  private final GameController gameController;
  private final PlayersNameAssignmentController playersNameAssignmentController;

  public MainController(
      BufferedReader bufferedReader,
      ResourceBundle resourceBundle,
      GameController gameController,
      PlayersNameAssignmentController playersNameAssignmentController) {
    this.bufferedReader = bufferedReader;
    this.resourceBundle = resourceBundle;
    this.gameController = gameController;
    this.playersNameAssignmentController = playersNameAssignmentController;
  }

  public void invoke()
      throws GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover, IOException {
    System.out.println();
    System.out.println(resourceBundle.getString("menu.description"));

    var numericSelection = this.bufferedReader.readLine();
    System.out.println();

    try {
      var menuSelection = MenuAction.values()[Integer.parseInt(numericSelection)];

      switch (menuSelection) {
        case ASSIGN_PLAYER_NAMES -> this.playersNameAssignmentController.invoke();
        case RESUME_GAME -> System.out.println(resourceBundle.getString("system.notImplemented"));
        case NEW_SINGLE_PLAYER_GAME -> this.gameController.playSinglePlayer();
        case NEW_MULTIPLAYER_GAME -> this.gameController.playMultiplayer();
        case EXIT -> {
          return;
        }
      }
    } catch (ArrayIndexOutOfBoundsException|NumberFormatException exception) {
      System.out.println(resourceBundle.getString("system.invalidSelection"));
    }

    this.invoke();
  }
}

package com.conizzoli.tictactoe.ui.controller;

import com.conizzoli.tictactoe.domain.exception.BoardLocationAlreadyMarkedException;
import com.conizzoli.tictactoe.domain.exception.GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover;
import com.conizzoli.tictactoe.domain.exception.InvalidBoardLocationException;
import com.conizzoli.tictactoe.domain.model.BoardLocation;
import com.conizzoli.tictactoe.domain.model.GameInterface;
import com.conizzoli.tictactoe.domain.model.MultiplayerGame;
import com.conizzoli.tictactoe.domain.model.SinglePlayerGame;
import com.conizzoli.tictactoe.domain.service.PlayerService;
import com.conizzoli.tictactoe.domain.service.PrinterService;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Random;
import java.util.ResourceBundle;

public class GameController {
  private final PrinterService printerService;
  private final PlayerService playerService;
  private final BufferedReader bufferedReader;
  private final ResourceBundle resourceBundle;

  public GameController(
      PrinterService printerService,
      PlayerService playerService,
      BufferedReader bufferedReader,
      ResourceBundle resourceBundle) {
    this.printerService = printerService;
    this.playerService = playerService;
    this.bufferedReader = bufferedReader;
    this.resourceBundle = resourceBundle;
  }

  public void playSinglePlayer()
      throws IOException, GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover {
    this.play(new SinglePlayerGame(new Random()));
  }

  public void playMultiplayer()
      throws IOException, GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover {
    this.play(new MultiplayerGame());
  }

  private void play(GameInterface game)
      throws IOException, GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover {
    while (game.isInProgress()) {
      this.requireMove(game);
    }

    this.printerService.printGameOutcome(game);
  }

  private void requireMove(GameInterface game)
      throws IOException, GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover {
    var player = game.getNextMovePlayer();

    this.printerService.printBoard(game);

    System.out.print(
        MessageFormat.format(
            resourceBundle.getString("game.requireMoveMessage"),
            playerService.getPlayerName(player)));
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
      System.out.println(
          MessageFormat.format(
              resourceBundle.getString("boardLocation.alreadyMarkedMessage"),
              exception.getPlayer().toSymbol()));
    }
  }
}

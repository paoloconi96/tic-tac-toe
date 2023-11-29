package com.conizzoli.tictactoe.ui.controller;

import com.conizzoli.tictactoe.application.service.CreateSinglePlayerGame;
import com.conizzoli.tictactoe.application.service.GetSinglePlayerGame;
import com.conizzoli.tictactoe.domain.exception.BoardLocationAlreadyMarkedException;
import com.conizzoli.tictactoe.domain.exception.GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover;
import com.conizzoli.tictactoe.domain.exception.InvalidBoardLocationException;
import com.conizzoli.tictactoe.domain.exception.SinglePlayerGameCouldNotBeFound;
import com.conizzoli.tictactoe.domain.model.BoardLocation;
import com.conizzoli.tictactoe.domain.model.GameInterface;
import com.conizzoli.tictactoe.domain.model.MultiplayerGame;
import com.conizzoli.tictactoe.domain.model.SinglePlayerGame;
import com.conizzoli.tictactoe.domain.repository.SinglePlayerIdGeneratorInterface;
import com.conizzoli.tictactoe.domain.service.PlayerService;
import com.conizzoli.tictactoe.domain.service.PrinterService;
import com.conizzoli.tictactoe.ui.exception.SinglePlayerGameCouldNotBeStarted;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ResourceBundle;

public class GameController {
  private final PrinterService printerService;
  private final PlayerService playerService;
  private final BufferedReader bufferedReader;
  private final ResourceBundle resourceBundle;
  private final CreateSinglePlayerGame createSinglePlayerGame;
  private final GetSinglePlayerGame getSinglePlayerGame;
  private final SinglePlayerIdGeneratorInterface singlePlayerIdGenerator;

  public GameController(
      PrinterService printerService,
      PlayerService playerService,
      BufferedReader bufferedReader,
      ResourceBundle resourceBundle,
      CreateSinglePlayerGame createSinglePlayerGame,
      GetSinglePlayerGame getSinglePlayerGame,
      SinglePlayerIdGeneratorInterface singlePlayerIdGenerator) {
    this.printerService = printerService;
    this.playerService = playerService;
    this.bufferedReader = bufferedReader;
    this.resourceBundle = resourceBundle;
    this.createSinglePlayerGame = createSinglePlayerGame;
    this.getSinglePlayerGame = getSinglePlayerGame;
    this.singlePlayerIdGenerator = singlePlayerIdGenerator;
  }

  public void playSinglePlayer()
      throws IOException, GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover {
    var gameId = singlePlayerIdGenerator.generateSinglePlayerGameId();
    this.createSinglePlayerGame.handle(gameId);

    SinglePlayerGame game;
    try {
      game = this.getSinglePlayerGame.handle(gameId);
    } catch (SinglePlayerGameCouldNotBeFound exception) {
      throw new SinglePlayerGameCouldNotBeStarted(exception);
    }

    this.play(game);
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

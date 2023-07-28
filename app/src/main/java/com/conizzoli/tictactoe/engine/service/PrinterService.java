package com.conizzoli.tictactoe.engine.service;

import com.conizzoli.tictactoe.engine.model.BoardLocation;
import com.conizzoli.tictactoe.engine.model.Game;
import com.google.inject.Inject;
import java.text.MessageFormat;
import java.util.ResourceBundle;

public class PrinterService {
  private final ResourceBundle resourceBundle;
  private final PlayerService playerService;

  @Inject
  public PrinterService(ResourceBundle resourceBundle, PlayerService playerService) {
    this.resourceBundle = resourceBundle;
    this.playerService = playerService;
  }

  public void printGameOutcome(Game game) {
    switch (game.getStatus()) {
      case WON -> this.printGameWinner(game);
      case DRAW -> System.out.println(resourceBundle.getString("game.drawMessage"));
      case IN_PROGRESS -> {}
    }
  }

  public void printGameWinner(Game game) {
    System.out.println(
        MessageFormat.format(
            resourceBundle.getString("game.winnerMessage"),
            this.playerService.getPlayerName(game.getWinner())));
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

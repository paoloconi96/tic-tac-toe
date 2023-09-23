package com.conizzoli.tictactoe.engine.service;

import com.conizzoli.tictactoe.engine.model.BoardLocation;
import com.conizzoli.tictactoe.engine.model.GameInterface;
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

  public void printGameOutcome(GameInterface game) {
    this.printBoard(game);

    switch (game.getStatus()) {
      case WON -> this.printGameWinner(game);
      case DRAW -> System.out.println(resourceBundle.getString("game.drawMessage"));
      case IN_PROGRESS -> {}
    }
  }

  public void printGameWinner(GameInterface game) {
    System.out.println(
        MessageFormat.format(
            resourceBundle.getString("game.winnerMessage"),
            this.playerService.getPlayerName(game.getWinner().get())));
  }

  public void printBoard(GameInterface game) {
    var stringBuilder = new StringBuilder();
    for (var i = 0; i < 3; i++) {
      for (var j = 0; j < 3; j++) {
        var state = game.getBoardLocationState(new BoardLocation(j, i));

        stringBuilder.append(state.isPresent() ? state.get().toSymbol() : j + (i * 3));
        stringBuilder.append(' ');
      }

      stringBuilder.append(System.lineSeparator());
    }

    System.out.println(stringBuilder);
  }
}

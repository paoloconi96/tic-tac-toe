package com.conizzoli.tictactoe.controller;

import com.conizzoli.tictactoe.engine.model.Player;
import com.conizzoli.tictactoe.engine.service.PlayerService;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ResourceBundle;

public class PlayersNameAssignmentController {
  private final PlayerService playerService;
  private final ResourceBundle resourceBundle;
  private final BufferedReader bufferedReader;

  public PlayersNameAssignmentController(
      PlayerService playerService, ResourceBundle resourceBundle, BufferedReader bufferedReader) {
    this.playerService = playerService;
    this.resourceBundle = resourceBundle;
    this.bufferedReader = bufferedReader;
  }

  public void invoke() throws IOException {
    this.updateNameFor(Player.CROSS);
    this.updateNameFor(Player.CIRCLE);

    System.out.println(
        MessageFormat.format(
            resourceBundle.getString("assignPlayerNames.currentStatusMessage"),
            this.playerService.getPlayerName(Player.CROSS),
            this.playerService.getPlayerName(Player.CIRCLE)));
  }

  private void updateNameFor(Player player)
      throws IOException {
    System.out.println(
        MessageFormat.format(
            resourceBundle.getString("assignPlayerNames.insertNewNameDescription"),
            player.name(),
            this.playerService.getPlayerName(player)));

    var newName = this.bufferedReader.readLine();
    if (newName.isEmpty()) {
      return;
    }

    this.playerService.assignName(player, newName);
  }
}

package com.conizzoli.tictactoe.ui.controller;

import com.conizzoli.tictactoe.engine.model.Player;
import com.conizzoli.tictactoe.engine.service.PlayerService;
import com.google.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.EnumMap;
import java.util.ResourceBundle;

public class PlayersNameAssignmentController {
  private final PlayerService playerService;
  private final ResourceBundle resourceBundle;
  private final BufferedReader bufferedReader;

  @Inject
  public PlayersNameAssignmentController(
      PlayerService playerService, ResourceBundle resourceBundle, BufferedReader bufferedReader) {
    this.playerService = playerService;
    this.resourceBundle = resourceBundle;
    this.bufferedReader = bufferedReader;
  }

  public void invoke() throws IOException {
    var playerNamesMap = this.playerService.getPlayerNamesMap();

    this.updateNameFor(Player.CROSS, playerNamesMap);
    this.updateNameFor(Player.CIRCLE, playerNamesMap);

    System.out.println(
        MessageFormat.format(
            resourceBundle.getString("assignPlayerNames.currentStatusMessage"),
            playerNamesMap.get(Player.CROSS),
            playerNamesMap.get(Player.CIRCLE)));
  }

  private void updateNameFor(Player player, EnumMap<Player, String> playerNamesMap)
      throws IOException {
    System.out.println(
        MessageFormat.format(
            resourceBundle.getString("assignPlayerNames.insertNewNameDescription"),
            player.name(),
            playerNamesMap.get(player)));

    var newName = this.bufferedReader.readLine();
    if (newName.equals("")) {
      return;
    }

    this.playerService.assignName(player, newName);
  }
}

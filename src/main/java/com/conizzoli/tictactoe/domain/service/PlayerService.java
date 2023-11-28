package com.conizzoli.tictactoe.domain.service;

import com.conizzoli.tictactoe.domain.model.Player;
import java.util.EnumMap;

public class PlayerService {
  EnumMap<Player, String> playerNamesMap = new EnumMap<>(Player.class);

  {
    this.playerNamesMap.put(Player.CIRCLE, "Circle");
    this.playerNamesMap.put(Player.CROSS, "Cross");
  }

  public void assignName(Player player, String newName) {
    this.playerNamesMap.put(player, newName);
  }
  
  public String getPlayerName(Player player) {
    return this.playerNamesMap.get(player);
  }
}

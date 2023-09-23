package com.conizzoli.tictactoe.engine.service;

import com.conizzoli.tictactoe.engine.model.Player;
import com.google.inject.Singleton;
import java.util.EnumMap;

@Singleton
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

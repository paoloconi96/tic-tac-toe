package com.conizzoli.tictactoe.engine.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.conizzoli.tictactoe.engine.model.Player;
import org.junit.jupiter.api.Test;

public class PlayerServiceTest {
    private final PlayerService playerService = new PlayerService();

    @Test
    void itAssignsName() {
        var playerCircleName = "Player Circle Name";

        this.playerService.assignName(Player.CIRCLE, playerCircleName);
        assertEquals(playerCircleName, this.playerService.getPlayerName(Player.CIRCLE));
    }

    @Test
    void itProvidesDefaultPlayerNames() {
        var playerNamesMap = this.playerService.getPlayerNamesMap();

        assertEquals(2, playerNamesMap.size());
        assertEquals("Circle", playerNamesMap.get(Player.CIRCLE));
        assertEquals("Cross", playerNamesMap.get(Player.CROSS));
    }
}

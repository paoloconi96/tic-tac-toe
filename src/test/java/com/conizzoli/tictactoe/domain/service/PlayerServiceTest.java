package com.conizzoli.tictactoe.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.conizzoli.tictactoe.domain.model.Player;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PlayerServiceTest {
    private final PlayerService playerService = new PlayerService();

    @ParameterizedTest
    @MethodSource("provideAssignNamesUseCases")
    void itAssignsNames(Player player, String name) {
        this.playerService.assignName(player, name);
        assertSame(name, this.playerService.getPlayerName(player));
    }

    static Stream<Object> provideAssignNamesUseCases() {
        return Stream.of(
          Arguments.of(Player.CIRCLE, "Circle Updated"),
          Arguments.of(Player.CROSS, "Cross Updated")
        );
    }

    @Test
    void itProvidesDefaultPlayerNames() {
        assertEquals("Circle", this.playerService.getPlayerName(Player.CIRCLE));
        assertEquals("Cross", this.playerService.getPlayerName(Player.CROSS));
    }
}

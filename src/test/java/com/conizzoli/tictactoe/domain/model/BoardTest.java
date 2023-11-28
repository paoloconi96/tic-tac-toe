package com.conizzoli.tictactoe.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import com.conizzoli.tictactoe.domain.exception.BoardLocationAlreadyMarkedException;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardTest {
    @Test
    void itComputesNotDrawIfFullWithWinner() throws BoardLocationAlreadyMarkedException {
        var board = new Board();
        assertFalse(board.computeIsDraw());

        board.mark(Player.CROSS, new BoardLocation(0, 0));
        board.mark(Player.CROSS, new BoardLocation(0, 1));
        board.mark(Player.CROSS, new BoardLocation(1, 0));
        board.mark(Player.CROSS, new BoardLocation(2, 0));
        board.mark(Player.CROSS, new BoardLocation(2, 1));

        board.mark(Player.CIRCLE, new BoardLocation(0, 2));
        board.mark(Player.CIRCLE, new BoardLocation(1, 1));
        board.mark(Player.CIRCLE, new BoardLocation(1, 2));
        board.mark(Player.CIRCLE, new BoardLocation(2, 2));

        assertFalse(board.computeIsDraw());
    }

    @Test
    void itComputesDrawIfFullWithoutWinner() throws BoardLocationAlreadyMarkedException {
        var board = new Board();
        assertFalse(board.computeIsDraw());

        board.mark(Player.CROSS, new BoardLocation(0, 1));
        board.mark(Player.CROSS, new BoardLocation(0, 2));
        board.mark(Player.CROSS, new BoardLocation(1, 0));
        board.mark(Player.CROSS, new BoardLocation(2, 1));
        board.mark(Player.CROSS, new BoardLocation(2, 2));

        board.mark(Player.CIRCLE, new BoardLocation(0, 0));
        board.mark(Player.CIRCLE, new BoardLocation(1, 1));
        board.mark(Player.CIRCLE, new BoardLocation(1, 2));
        board.mark(Player.CIRCLE, new BoardLocation(2, 0));

        assertTrue(board.computeIsDraw());
    }

    @Test
    void itComputesRowWin() throws BoardLocationAlreadyMarkedException {
        var board = new Board();
        assertEquals(Optional.empty(), board.computeWinner());

        board.mark(Player.CROSS, new BoardLocation(0, 0));
        board.mark(Player.CROSS, new BoardLocation(0, 1));
        board.mark(Player.CROSS, new BoardLocation(0, 2));

        assertEquals(Optional.of(Player.CROSS), board.computeWinner());
    }

    @Test
    void itComputesColumWin() throws BoardLocationAlreadyMarkedException {
        var board = new Board();
        assertEquals(Optional.empty(), board.computeWinner());

        board.mark(Player.CROSS, new BoardLocation(0, 0));
        board.mark(Player.CROSS, new BoardLocation(1, 0));
        board.mark(Player.CROSS, new BoardLocation(2, 0));

        assertEquals(Optional.of(Player.CROSS), board.computeWinner());
    }

    @Test
    void itComputesDiagonalWin() throws BoardLocationAlreadyMarkedException {
        var board = new Board();
        assertEquals(Optional.empty(), board.computeWinner());

        board.mark(Player.CROSS, new BoardLocation(0, 0));
        board.mark(Player.CROSS, new BoardLocation(1, 1));
        board.mark(Player.CROSS, new BoardLocation(2, 2));

        assertEquals(Optional.of(Player.CROSS), board.computeWinner());
    }

    @Test
    void itComputesInvertedDiagonalWin() throws BoardLocationAlreadyMarkedException {
        var board = new Board();
        assertEquals(Optional.empty(), board.computeWinner());

        board.mark(Player.CROSS, new BoardLocation(0, 2));
        board.mark(Player.CROSS, new BoardLocation(1, 1));
        board.mark(Player.CROSS, new BoardLocation(2, 0));

        assertEquals(Optional.of(Player.CROSS), board.computeWinner());
    }

    @Test
    void itMarks() throws BoardLocationAlreadyMarkedException {
        var board = new Board();
        var boardLocation = new BoardLocation(0, 0);

        Assertions.assertFalse(board.getBoardLocationState(boardLocation).isPresent());

        board.mark(Player.CROSS, boardLocation);

        Assertions.assertEquals(Optional.of(Player.CROSS), board.getBoardLocationState(boardLocation));
    }

    @Test
    void itThrowsWhenBoardLocationIsAlreadyMarked() throws BoardLocationAlreadyMarkedException {
        var board = new Board();
        var boardLocation = new BoardLocation(0, 0);

        board.mark(Player.CROSS, boardLocation);
        Assertions.assertTrue(board.getBoardLocationState(boardLocation).isPresent());

        assertThrows(BoardLocationAlreadyMarkedException.class, () -> {
            board.mark(Player.CROSS, boardLocation);
        });
    }
}

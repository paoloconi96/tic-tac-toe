package com.conizzoli.tictactoe.engine.model;

import static org.junit.jupiter.api.Assertions.*;

import com.conizzoli.tictactoe.engine.exception.BoardLocationAlreadyMarkedException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;

public class BoardTest {
    @Test
    void itComputesDraw() throws BoardLocationAlreadyMarkedException {
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

        assertTrue(board.computeIsDraw());
    }

    @Test
    @ValueSource(ints = {1, 3, 5, -3, 15, Integer.MAX_VALUE}) // six numbers
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

        assertNull(board.getBoardLocationState(boardLocation));

        board.mark(Player.CROSS, boardLocation);

        assertEquals(Player.CROSS, board.getBoardLocationState(boardLocation));
    }

    @Test
    void itThrowsWhenBoardLocationIsAlreadyMarked() throws BoardLocationAlreadyMarkedException {
        var board = new Board();
        var boardLocation = new BoardLocation(0, 0);

        board.mark(Player.CROSS, boardLocation);
        assertNotNull(board.getBoardLocationState(boardLocation));

        assertThrows(BoardLocationAlreadyMarkedException.class, () -> {
            board.mark(Player.CROSS, boardLocation);
        });
    }
}

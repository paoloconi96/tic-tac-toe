package com.conizzoli.tictactoe.domain.service;

import com.conizzoli.tictactoe.domain.model.BoardLocation;
import com.conizzoli.tictactoe.domain.model.GameStatus;
import com.conizzoli.tictactoe.domain.model.Player;
import com.conizzoli.tictactoe.domain.model.SinglePlayerGame;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Optional;
import java.util.ResourceBundle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PrinterServiceTest {
  private final PrintStream originalOutputStream = System.out;
  private final ByteArrayOutputStream mockedOutputStream = new ByteArrayOutputStream();
  @Mock
  private ResourceBundle resourceBundle;
  @Mock
  private PlayerService playerService;
  @Mock
  private SinglePlayerGame game;

  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(this.mockedOutputStream));
  }

  @AfterEach
  public void tearDown() {
    System.setOut(this.originalOutputStream);
  }

  @Test
  public void testPrintInProgressGameOutcome() {
    var printerService = new PrinterService(this.resourceBundle, this.playerService);

    Mockito.when(this.game.getBoardLocationState(Mockito.any(BoardLocation.class))).thenReturn(Optional.of(Player.CROSS));
    Mockito.when(this.game.getStatus()).thenReturn(GameStatus.IN_PROGRESS);

    printerService.printGameOutcome(this.game);

    Assertions.assertEquals(
      "X X X \nX X X \nX X X",
      this.mockedOutputStream.toString().trim()
    );
  }

  @Test
  public void testPrintWonGameOutcome() {
    var printerService = new PrinterService(this.resourceBundle, this.playerService);

    Mockito.when(this.game.getBoardLocationState(Mockito.any(BoardLocation.class))).thenReturn(Optional.of(Player.CROSS));
    Mockito.when(this.game.getStatus()).thenReturn(GameStatus.WON);
    Mockito.when(this.resourceBundle.getString("game.winnerMessage")).thenReturn("Winner: {0}");
    Mockito.when(this.game.getWinner()).thenReturn(Optional.of(Player.CIRCLE));
    Mockito.when(this.playerService.getPlayerName(Player.CIRCLE)).thenReturn("Circle");

    printerService.printGameOutcome(this.game);

    Assertions.assertEquals(
        "X X X \nX X X \nX X X \n\nWinner: Circle",
        this.mockedOutputStream.toString().trim()
    );
  }

  @Test
  public void testPrintDrawGameOutcome() {
    var printerService = new PrinterService(this.resourceBundle, this.playerService);

    Mockito.when(this.game.getBoardLocationState(Mockito.any(BoardLocation.class))).thenReturn(Optional.of(Player.CROSS));
    Mockito.when(this.game.getStatus()).thenReturn(GameStatus.DRAW);
    Mockito.when(this.resourceBundle.getString("game.drawMessage")).thenReturn("Draw");

    printerService.printGameOutcome(this.game);

    Assertions.assertEquals(
        "X X X \nX X X \nX X X \n\nDraw",
        this.mockedOutputStream.toString().trim());
  }

  @Test
  public void testPrintGameWinner() {
    var printerService = new PrinterService(this.resourceBundle, this.playerService);
    Mockito.when(this.resourceBundle.getString("game.winnerMessage")).thenReturn("Winner: {0}");
    Mockito.when(this.game.getWinner()).thenReturn(Optional.of(Player.CIRCLE));
    Mockito.when(this.playerService.getPlayerName(Player.CIRCLE)).thenReturn("Circle");

    printerService.printGameWinner(this.game);
    Assertions.assertEquals("Winner: Circle", this.mockedOutputStream.toString().trim());
  }

  @Test
  public void testPrintBoard() {
    var printerService = new PrinterService(this.resourceBundle, this.playerService);
    Mockito.when(this.game.getBoardLocationState(new BoardLocation(0, 2))).thenReturn(Optional.of(Player.CROSS));
    Mockito.when(this.game.getBoardLocationState(new BoardLocation(1, 0))).thenReturn(Optional.of(Player.CROSS));
    Mockito.when(this.game.getBoardLocationState(new BoardLocation(2, 0))).thenReturn(Optional.of(Player.CROSS));

    printerService.printBoard(this.game);
    Assertions.assertEquals("0 X X \n3 4 5 \nX 7 8", this.mockedOutputStream.toString().trim());
  }
}

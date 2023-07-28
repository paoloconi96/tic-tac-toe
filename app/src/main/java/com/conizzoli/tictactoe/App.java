package com.conizzoli.tictactoe;

import com.conizzoli.tictactoe.engine.exception.GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover;
import com.conizzoli.tictactoe.ui.controller.MainController;
import com.google.inject.Guice;
import com.google.inject.Injector;
import java.io.IOException;

public class App {
  public static void main(String[] args)
      throws IOException, GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover {
    Injector injector = Guice.createInjector(new DiModule());
    injector.getInstance(MainController.class).invoke();
  }
}

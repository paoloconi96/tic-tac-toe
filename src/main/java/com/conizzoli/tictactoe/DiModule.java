package com.conizzoli.tictactoe;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class DiModule extends AbstractModule {
  @Provides
  static BufferedReader provideBufferedReader() {
    return new BufferedReader(new InputStreamReader(System.in));
  }

  @Provides
  static ResourceBundle provideResourceBundle() {
    return ResourceBundle.getBundle("TicTacToeBundle");
  }
}

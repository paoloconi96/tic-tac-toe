package com.conizzoli.tictactoe;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class CustomBeansFactory {
  public BufferedReader createBufferedReader() {
    return new BufferedReader(new InputStreamReader(System.in));
  }

  public ResourceBundle createResourceBundle() {
    return ResourceBundle.getBundle("TicTacToeBundle");
  }
}

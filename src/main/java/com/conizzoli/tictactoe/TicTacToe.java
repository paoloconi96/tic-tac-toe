package com.conizzoli.tictactoe;

import com.conizzoli.tictactoe.controller.MainController;
import com.conizzoli.tictactoe.engine.exception.GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover;
import java.io.IOException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TicTacToe {
  public static void main(String[] args)
      throws GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover, IOException {
    var context = new ClassPathXmlApplicationContext("beans.xml");
    context.getBean(MainController.class).invoke();
  }
}

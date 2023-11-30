package com.conizzoli.tictactoe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:beans.xml")
public class TicTacToeWeb {
  public static void main(String[] args) {
    SpringApplication.run(TicTacToeWeb.class, args);
  }
}

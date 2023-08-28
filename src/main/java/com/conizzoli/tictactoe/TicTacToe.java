package com.conizzoli.tictactoe;

import com.conizzoli.tictactoe.controller.MainController;
import com.conizzoli.tictactoe.engine.exception.GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class TicTacToe {
	public static void main(String[] args) throws GameBoardLocationCouldNotBeMarkedBecausePlayerIsNotNextMover, IOException {
		Injector injector = Guice.createInjector(new DiModule());
		injector.getInstance(MainController.class).invoke();

//		SpringApplication.run(TicTacToe.class, args);
	}
}

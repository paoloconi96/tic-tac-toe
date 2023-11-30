package com.conizzoli.tictactoe.ui.web.controller;

import com.conizzoli.tictactoe.application.service.CreateSinglePlayerGame;
import com.conizzoli.tictactoe.application.service.GetSinglePlayerGame;
import com.conizzoli.tictactoe.domain.exception.SinglePlayerGameCouldNotBeFound;
import com.conizzoli.tictactoe.domain.model.SinglePlayerGame;
import com.conizzoli.tictactoe.domain.repository.SinglePlayerIdGeneratorInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CreateSinglePlayerGameController {
  @Autowired SinglePlayerIdGeneratorInterface singlePlayerIdGenerator;

  @Autowired CreateSinglePlayerGame createSinglePlayerGame;

  @Autowired GetSinglePlayerGame getSinglePlayerGame;

  @GetMapping(name = "game.single_player.create", path = "/api/v1/game/single-player")
  @ResponseStatus(HttpStatus.CREATED)
  public SinglePlayerGame handle() throws JsonProcessingException {
    var gameId = this.singlePlayerIdGenerator.generateSinglePlayerGameId();
    this.createSinglePlayerGame.handle(gameId);

    SinglePlayerGame game;
    try {
      game = this.getSinglePlayerGame.handle(gameId);
    } catch (SinglePlayerGameCouldNotBeFound exception) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    return game;
  }
}

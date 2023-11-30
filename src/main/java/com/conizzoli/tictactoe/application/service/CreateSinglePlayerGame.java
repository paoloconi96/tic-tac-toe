package com.conizzoli.tictactoe.application.service;

import com.conizzoli.tictactoe.domain.model.SinglePlayerGame;
import com.conizzoli.tictactoe.domain.model.SinglePlayerGameId;
import com.conizzoli.tictactoe.domain.repository.SinglePlayerRepositoryInterface;
import java.util.Random;
import org.springframework.lang.NonNull;

public class CreateSinglePlayerGame {
  private final SinglePlayerRepositoryInterface singlePlayerRepository;

  public CreateSinglePlayerGame(SinglePlayerRepositoryInterface singlePlayerRepository) {
    this.singlePlayerRepository = singlePlayerRepository;
  }

  public void handle(@NonNull SinglePlayerGameId id) {
    var game = new SinglePlayerGame(id, new Random());
    this.singlePlayerRepository.save(game);
  }
}

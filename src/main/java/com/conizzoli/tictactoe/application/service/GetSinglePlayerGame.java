package com.conizzoli.tictactoe.application.service;

import com.conizzoli.tictactoe.domain.exception.SinglePlayerGameCouldNotBeFound;
import com.conizzoli.tictactoe.domain.model.SinglePlayerGame;
import com.conizzoli.tictactoe.domain.model.SinglePlayerGameId;
import com.conizzoli.tictactoe.domain.repository.SinglePlayerRepositoryInterface;
import org.springframework.lang.NonNull;

public class GetSinglePlayerGame {
  private final SinglePlayerRepositoryInterface singlePlayerRepository;

  public GetSinglePlayerGame(SinglePlayerRepositoryInterface singlePlayerRepository) {
    this.singlePlayerRepository = singlePlayerRepository;
  }

  public SinglePlayerGame handle(@NonNull SinglePlayerGameId id)
      throws SinglePlayerGameCouldNotBeFound {
    return this.singlePlayerRepository.findById(id);
  }
}

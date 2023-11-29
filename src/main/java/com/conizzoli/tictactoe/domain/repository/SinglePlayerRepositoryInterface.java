package com.conizzoli.tictactoe.domain.repository;

import com.conizzoli.tictactoe.domain.exception.SinglePlayerGameCouldNotBeFound;
import com.conizzoli.tictactoe.domain.model.SinglePlayerGame;
import com.conizzoli.tictactoe.domain.model.SinglePlayerGameId;

public interface SinglePlayerRepositoryInterface {
  void save(SinglePlayerGame game);

  SinglePlayerGame findById(SinglePlayerGameId id) throws SinglePlayerGameCouldNotBeFound;
}

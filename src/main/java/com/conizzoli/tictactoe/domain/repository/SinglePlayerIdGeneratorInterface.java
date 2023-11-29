package com.conizzoli.tictactoe.domain.repository;

import com.conizzoli.tictactoe.domain.model.SinglePlayerGameId;

public interface SinglePlayerIdGeneratorInterface {
  public SinglePlayerGameId generateSinglePlayerGameId();
}

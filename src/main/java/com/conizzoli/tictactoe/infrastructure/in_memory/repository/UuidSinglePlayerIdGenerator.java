package com.conizzoli.tictactoe.infrastructure.in_memory.repository;

import com.conizzoli.tictactoe.domain.model.SinglePlayerGameId;
import com.conizzoli.tictactoe.domain.repository.SinglePlayerIdGeneratorInterface;
import java.util.UUID;

public class UuidSinglePlayerIdGenerator implements SinglePlayerIdGeneratorInterface {
  @Override
  public SinglePlayerGameId generateSinglePlayerGameId() {
    return new SinglePlayerGameId(UUID.randomUUID());
  }
}

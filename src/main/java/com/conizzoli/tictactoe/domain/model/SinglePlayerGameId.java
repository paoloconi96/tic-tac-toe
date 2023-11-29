package com.conizzoli.tictactoe.domain.model;

import com.conizzoli.shared.domain.AbstractUuid;

import java.util.UUID;

public class SinglePlayerGameId extends AbstractUuid {
  public SinglePlayerGameId(UUID value) {
    super(value);
  }

  public SinglePlayerGameId(String value) {
    super(value);
  }
}

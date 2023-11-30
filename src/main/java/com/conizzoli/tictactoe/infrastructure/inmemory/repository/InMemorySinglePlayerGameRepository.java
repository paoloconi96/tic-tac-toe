package com.conizzoli.tictactoe.infrastructure.inmemory.repository;

import com.conizzoli.tictactoe.domain.exception.SinglePlayerGameCouldNotBeFound;
import com.conizzoli.tictactoe.domain.model.SinglePlayerGame;
import com.conizzoli.tictactoe.domain.model.SinglePlayerGameId;
import com.conizzoli.tictactoe.domain.repository.SinglePlayerRepositoryInterface;
import java.util.HashMap;
import java.util.Map;
import org.springframework.lang.NonNull;

public class InMemorySinglePlayerGameRepository implements SinglePlayerRepositoryInterface {
  private final Map<SinglePlayerGameId, SinglePlayerGame> games = new HashMap<>();

  @Override
  public void save(@NonNull SinglePlayerGame game) {
    this.games.put(game.getId(), game);
  }

  @Override
  public @NonNull SinglePlayerGame findById(@NonNull SinglePlayerGameId id)
      throws SinglePlayerGameCouldNotBeFound {
    var game = this.games.get(id);

    if (game == null) {
      throw new SinglePlayerGameCouldNotBeFound(id, null);
    }

    return game;
  }
}

package com.conizzoli.shared.domain;

import java.util.UUID;

public abstract class AbstractUuid {
  private final UUID value;

  public AbstractUuid(UUID value) {
    this.value = value;
  }

  public AbstractUuid(String value) {
    this.value = UUID.fromString(value);
  }

  @Override
  public String toString() {
    return this.value.toString();
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof AbstractUuid id) {
      return this.value.equals(id.value);
    }

    return false;
  }
}

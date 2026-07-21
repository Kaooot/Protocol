package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum ItemUseClientCooldownState {
  OFF,

  ON;

  private static final ItemUseClientCooldownState[] VALUES = values();

  public static ItemUseClientCooldownState from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown ItemUseClientCooldownState ID: " + ordinal);
  }
}

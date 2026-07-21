package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum PlayerRespawnState {
  SEARCHING_FOR_SPAWN,

  READY_TO_SPAWN,

  CLIENT_READY_TO_SPAWN;

  private static final PlayerRespawnState[] VALUES = values();

  public static PlayerRespawnState from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown PlayerRespawnState ID: " + ordinal);
  }
}

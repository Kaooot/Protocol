package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum PlayerRespawnState {
  /**
   * SearchingForSpawn
   */
  SEARCHING_FOR_SPAWN,

  /**
   * ReadyToSpawn
   */
  READY_TO_SPAWN,

  /**
   * ClientReadyToSpawn
   */
  CLIENT_READY_TO_SPAWN;

  private static final PlayerRespawnState[] VALUES = values();

  public static PlayerRespawnState from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown PlayerRespawnState ID: " + ordinal);
  }
}

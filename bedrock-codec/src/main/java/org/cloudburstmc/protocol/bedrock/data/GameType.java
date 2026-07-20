package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum GameType {
  UNDEFINED,

  SURVIVAL,

  CREATIVE,

  ADVENTURE,

  DEFAULT,

  SPECTATOR,

  WORLD_DEFAULT;

  private static final GameType[] VALUES = values();

  public static GameType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown GameType ID: " + ordinal);
  }
}

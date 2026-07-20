package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum SpawnPositionType {
  PLAYER_RESPAWN,

  WORLD_SPAWN;

  private static final SpawnPositionType[] VALUES = values();

  public static SpawnPositionType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown SpawnPositionType ID: " + ordinal);
  }
}

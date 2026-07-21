package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

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

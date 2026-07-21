package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum BossEventUpdateType {
  ADD,

  PLAYER_ADDED,

  REMOVE,

  PLAYER_REMOVED,

  UPDATE_PERCENT,

  UPDATE_NAME,

  UPDATE_PROPERTIES,

  UPDATE_STYLE,

  QUERY;

  private static final BossEventUpdateType[] VALUES = values();

  public static BossEventUpdateType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown BossEventUpdateType ID: " + ordinal);
  }
}

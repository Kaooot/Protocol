package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum ScoreboardIdentityPacketType {
  UPDATE,

  REMOVE;

  private static final ScoreboardIdentityPacketType[] VALUES = values();

  public static ScoreboardIdentityPacketType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown ScoreboardIdentityPacketType ID: " + ordinal);
  }
}

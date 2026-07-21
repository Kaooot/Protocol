package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum AnimatePacketPayloadAction {
  NO_ACTION,

  SWING,

  WAKE_UP,

  CRITICAL_HIT,

  MAGIC_CRITICAL_HIT;

  private static final AnimatePacketPayloadAction[] VALUES = values();

  public static AnimatePacketPayloadAction from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown AnimatePacketPayloadAction ID: " + ordinal);
  }
}

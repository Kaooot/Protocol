package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum ClientboundTextureShiftPacketPayloadAction {
  INVALID,

  INITIALIZE,

  START,

  SET_ENABLED,

  SYNC;

  private static final ClientboundTextureShiftPacketPayloadAction[] VALUES = values();

  public static ClientboundTextureShiftPacketPayloadAction from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown ClientboundTextureShiftPacketPayloadAction ID: " + ordinal);
  }
}

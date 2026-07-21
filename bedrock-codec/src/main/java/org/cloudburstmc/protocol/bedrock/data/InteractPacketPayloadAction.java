package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum InteractPacketPayloadAction {
  INVALID,

  STOP_RIDING,

  INTERACT_UPDATE,

  NPC_OPEN,

  OPEN_INVENTORY;

  private static final InteractPacketPayloadAction[] VALUES = values();

  public static InteractPacketPayloadAction from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown InteractPacketPayloadAction ID: " + ordinal);
  }
}

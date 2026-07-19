package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum InteractPacketPayloadAction {
  /**
   * Invalid
   */
  INVALID,

  /**
   * StopRiding
   */
  STOP_RIDING,

  /**
   * InteractUpdate
   */
  INTERACT_UPDATE,

  /**
   * NpcOpen
   */
  NPC_OPEN,

  /**
   * OpenInventory
   */
  OPEN_INVENTORY;

  private static final InteractPacketPayloadAction[] VALUES = values();

  public static InteractPacketPayloadAction from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown InteractPacketPayloadAction ID: " + ordinal);
  }
}

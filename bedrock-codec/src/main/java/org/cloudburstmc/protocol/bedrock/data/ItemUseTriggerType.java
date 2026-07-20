package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum ItemUseTriggerType {
  UNKNOWN,

  PLAYER_INPUT,

  SIMULATION_TICK;

  private static final ItemUseTriggerType[] VALUES = values();

  public static ItemUseTriggerType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown ItemUseTriggerType ID: " + ordinal);
  }
}

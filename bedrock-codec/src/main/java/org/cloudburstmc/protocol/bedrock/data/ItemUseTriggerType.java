package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

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

package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum InteractionType {
  BREEDING,

  TAMING,

  CURING,

  CRAFTED,

  SHEARING,

  MILKING,

  TRADING,

  FEEDING,

  IGNITING,

  COLORING,

  NAMING,

  LEASHING,

  UNLEASHING,

  PET_SLEEP,

  TRUSTING,

  COMMANDING,

  EQUIPPING;

  private static final InteractionType[] VALUES = values();

  public static InteractionType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown InteractionType ID: " + ordinal);
  }
}

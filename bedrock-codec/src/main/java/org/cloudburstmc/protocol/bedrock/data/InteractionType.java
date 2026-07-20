package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
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

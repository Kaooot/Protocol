package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum ArmorSlot {
  HEAD,

  TORSO,

  LEGS,

  FEET,

  BODY;

  private static final ArmorSlot[] VALUES = values();

  public static ArmorSlot from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown ArmorSlot ID: " + ordinal);
  }
}

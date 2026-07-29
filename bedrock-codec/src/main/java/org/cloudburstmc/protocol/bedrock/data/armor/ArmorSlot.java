package org.cloudburstmc.protocol.bedrock.data.armor;

import java.lang.UnsupportedOperationException;

/**
 * All available armor slots. 'Body' is used for horses etc
 *
 * @since v844
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

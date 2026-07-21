package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum VillageType {
  DESERT,

  ICE,

  SAVANNA,

  TAIGA,

  DEFAULT;

  private static final VillageType[] VALUES = values();

  public static VillageType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown VillageType ID: " + ordinal);
  }
}

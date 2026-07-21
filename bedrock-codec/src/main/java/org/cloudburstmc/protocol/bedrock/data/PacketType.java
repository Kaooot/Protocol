package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum PacketType {
  PLAYER_LOCATION_HIDE;

  private static final PacketType[] VALUES = values();

  public static PacketType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown PacketType ID: " + ordinal);
  }
}

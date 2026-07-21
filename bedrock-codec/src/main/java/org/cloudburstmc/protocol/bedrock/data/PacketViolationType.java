package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum PacketViolationType {
  UNKNOWN,

  PACKET_MALFORMED;

  private static final PacketViolationType[] VALUES = values();

  public static PacketViolationType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown PacketViolationType ID: " + ordinal);
  }
}

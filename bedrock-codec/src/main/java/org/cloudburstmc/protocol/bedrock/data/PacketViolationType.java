package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum PacketViolationType {
  /**
   * Unknown
   */
  UNKNOWN,

  /**
   * PacketMalformed
   */
  PACKET_MALFORMED;

  private static final PacketViolationType[] VALUES = values();

  public static PacketViolationType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown PacketViolationType ID: " + ordinal);
  }
}

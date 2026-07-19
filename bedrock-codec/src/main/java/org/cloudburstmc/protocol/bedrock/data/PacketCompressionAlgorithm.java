package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum PacketCompressionAlgorithm {
  /**
   * ZLib
   */
  ZLIB,

  /**
   * Snappy
   */
  SNAPPY,

  /**
   * None
   */
  NONE;

  private static final PacketCompressionAlgorithm[] VALUES = values();

  public static PacketCompressionAlgorithm from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown PacketCompressionAlgorithm ID: " + ordinal);
  }
}

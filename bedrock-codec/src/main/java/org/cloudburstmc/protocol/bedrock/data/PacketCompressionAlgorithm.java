package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum PacketCompressionAlgorithm {
  ZLIB,

  SNAPPY,

  NONE;

  private static final PacketCompressionAlgorithm[] VALUES = values();

  public static PacketCompressionAlgorithm from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown PacketCompressionAlgorithm ID: " + ordinal);
  }
}

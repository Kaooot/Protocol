package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum LoadingScreenPacketType {
  START_LOADING_SCREEN,

  END_LOADING_SCREEN;

  private static final LoadingScreenPacketType[] VALUES = values();

  public static LoadingScreenPacketType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown LoadingScreenPacketType ID: " + ordinal);
  }
}

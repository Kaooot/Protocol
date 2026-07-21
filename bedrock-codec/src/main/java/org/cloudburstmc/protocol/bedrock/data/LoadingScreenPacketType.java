package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

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

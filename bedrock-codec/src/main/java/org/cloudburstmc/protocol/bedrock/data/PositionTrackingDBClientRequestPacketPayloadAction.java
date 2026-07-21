package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum PositionTrackingDBClientRequestPacketPayloadAction {
  QUERY;

  private static final PositionTrackingDBClientRequestPacketPayloadAction[] VALUES = values();

  public static PositionTrackingDBClientRequestPacketPayloadAction from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown PositionTrackingDBClientRequestPacketPayloadAction ID: " + ordinal);
  }
}

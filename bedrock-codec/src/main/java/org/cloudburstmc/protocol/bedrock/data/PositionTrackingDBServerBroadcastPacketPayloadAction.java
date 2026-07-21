package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum PositionTrackingDBServerBroadcastPacketPayloadAction {
  UPDATE,

  DESTROY,

  NOT_FOUND;

  private static final PositionTrackingDBServerBroadcastPacketPayloadAction[] VALUES = values();

  public static PositionTrackingDBServerBroadcastPacketPayloadAction from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown PositionTrackingDBServerBroadcastPacketPayloadAction ID: " + ordinal);
  }
}

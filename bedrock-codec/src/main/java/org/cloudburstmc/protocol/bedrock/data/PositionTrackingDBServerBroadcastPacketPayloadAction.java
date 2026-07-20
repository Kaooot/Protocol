package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
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

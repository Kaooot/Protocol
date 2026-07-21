package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum CameraAimAssistPacketPayloadTargetMode {
  ANGLE,

  DISTANCE;

  private static final CameraAimAssistPacketPayloadTargetMode[] VALUES = values();

  public static CameraAimAssistPacketPayloadTargetMode from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown CameraAimAssistPacketPayloadTargetMode ID: " + ordinal);
  }
}

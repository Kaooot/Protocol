package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum CameraAimAssistPacketPayloadAction {
  SET,

  CLEAR;

  private static final CameraAimAssistPacketPayloadAction[] VALUES = values();

  public static CameraAimAssistPacketPayloadAction from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown CameraAimAssistPacketPayloadAction ID: " + ordinal);
  }
}

package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum ClientCameraAimAssistPacketAction {
  SET_FROM_CAMERA_PRESET,

  CLEAR;

  private static final ClientCameraAimAssistPacketAction[] VALUES = values();

  public static ClientCameraAimAssistPacketAction from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown ClientCameraAimAssistPacketAction ID: " + ordinal);
  }
}

package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

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

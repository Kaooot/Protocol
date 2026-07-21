package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum CameraShakeAction {
  ADD,

  STOP;

  private static final CameraShakeAction[] VALUES = values();

  public static CameraShakeAction from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown CameraShakeAction ID: " + ordinal);
  }
}

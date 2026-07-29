package org.cloudburstmc.protocol.bedrock.data.camera.aimassist;

import java.lang.UnsupportedOperationException;

public enum AimAssistTargetMode {
  ANGLE,

  DISTANCE;

  private static final AimAssistTargetMode[] VALUES = values();

  public static AimAssistTargetMode from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown AimAssistTargetMode ID: " + ordinal);
  }
}

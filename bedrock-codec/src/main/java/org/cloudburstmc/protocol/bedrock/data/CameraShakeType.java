package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum CameraShakeType {
  POSITIONAL,

  ROTATIONAL;

  private static final CameraShakeType[] VALUES = values();

  public static CameraShakeType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown CameraShakeType ID: " + ordinal);
  }
}

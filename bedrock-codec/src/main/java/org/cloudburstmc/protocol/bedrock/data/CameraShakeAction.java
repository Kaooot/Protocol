package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum CameraShakeAction {
  /**
   * Add
   */
  ADD,

  /**
   * Stop
   */
  STOP;

  private static final CameraShakeAction[] VALUES = values();

  public static CameraShakeAction from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown CameraShakeAction ID: " + ordinal);
  }
}

package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum SharedTypesComprehensiveCameraPresetAudioListener {
  /**
   * Camera
   */
  CAMERA,

  /**
   * Player
   */
  PLAYER;

  private static final SharedTypesComprehensiveCameraPresetAudioListener[] VALUES = values();

  public static SharedTypesComprehensiveCameraPresetAudioListener from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown SharedTypesComprehensiveCameraPresetAudioListener ID: " + ordinal);
  }
}

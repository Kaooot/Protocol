package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum NewInteractionModel {
  TOUCH,

  CROSSHAIR,

  CLASSIC,

  COUNT;

  private static final NewInteractionModel[] VALUES = values();

  public static NewInteractionModel from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown NewInteractionModel ID: " + ordinal);
  }
}

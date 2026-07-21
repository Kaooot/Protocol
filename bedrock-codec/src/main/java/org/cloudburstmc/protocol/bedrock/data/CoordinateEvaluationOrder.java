package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum CoordinateEvaluationOrder {
  XYZ,

  XZY,

  YXZ,

  YZX,

  ZXY,

  ZYX;

  private static final CoordinateEvaluationOrder[] VALUES = values();

  public static CoordinateEvaluationOrder from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown CoordinateEvaluationOrder ID: " + ordinal);
  }
}

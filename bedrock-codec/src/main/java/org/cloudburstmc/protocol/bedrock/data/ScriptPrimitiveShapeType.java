package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum ScriptPrimitiveShapeType {
  LINE,

  BOX,

  SPHERE,

  CIRCLE,

  TEXT,

  ARROW,

  CYLINDER,

  PYRAMID,

  ELLIPSOID,

  CONE;

  private static final ScriptPrimitiveShapeType[] VALUES = values();

  public static ScriptPrimitiveShapeType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown ScriptPrimitiveShapeType ID: " + ordinal);
  }
}

package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
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

package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum ScriptModuleMinecraftScriptPrimitiveShapeType {
  /**
   * Line
   */
  LINE,

  /**
   * Box
   */
  BOX,

  /**
   * Sphere
   */
  SPHERE,

  /**
   * Circle
   */
  CIRCLE,

  /**
   * Text
   */
  TEXT,

  /**
   * Arrow
   */
  ARROW,

  /**
   * Cylinder
   */
  CYLINDER,

  /**
   * Pyramid
   */
  PYRAMID,

  /**
   * Ellipsoid
   */
  ELLIPSOID,

  /**
   * Cone
   */
  CONE;

  private static final ScriptModuleMinecraftScriptPrimitiveShapeType[] VALUES = values();

  public static ScriptModuleMinecraftScriptPrimitiveShapeType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown ScriptModuleMinecraftScriptPrimitiveShapeType ID: " + ordinal);
  }
}

package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum StructureTemplateResponseType {
  NONE,

  EXPORT,

  QUERY;

  private static final StructureTemplateResponseType[] VALUES = values();

  public static StructureTemplateResponseType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown StructureTemplateResponseType ID: " + ordinal);
  }
}

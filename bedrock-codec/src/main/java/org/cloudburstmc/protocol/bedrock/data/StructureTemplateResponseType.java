package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

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

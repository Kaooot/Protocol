package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum StructureTemplateRequestOperation {
  NONE,

  EXPORT_FROM_SAVE_MODE,

  EXPORT_FROM_LOAD_MODE,

  QUERY_SAVED_STRUCTURE;

  private static final StructureTemplateRequestOperation[] VALUES = values();

  public static StructureTemplateRequestOperation from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown StructureTemplateRequestOperation ID: " + ordinal);
  }
}

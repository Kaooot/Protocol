package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum StructureTemplateRequestOperation {
  /**
   * None
   */
  NONE,

  /**
   * ExportFromSaveMode
   */
  EXPORT_FROM_SAVE_MODE,

  /**
   * ExportFromLoadMode
   */
  EXPORT_FROM_LOAD_MODE,

  /**
   * QuerySavedStructure
   */
  QUERY_SAVED_STRUCTURE;

  private static final StructureTemplateRequestOperation[] VALUES = values();

  public static StructureTemplateRequestOperation from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown StructureTemplateRequestOperation ID: " + ordinal);
  }
}

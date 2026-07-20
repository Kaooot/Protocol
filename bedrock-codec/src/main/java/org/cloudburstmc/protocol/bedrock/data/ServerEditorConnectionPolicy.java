package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum ServerEditorConnectionPolicy {
  MATCH_WORLD_TYPE,

  EDITOR_ONLY,

  VANILLA_ONLY,

  MIXED;

  private static final ServerEditorConnectionPolicy[] VALUES = values();

  public static ServerEditorConnectionPolicy from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown ServerEditorConnectionPolicy ID: " + ordinal);
  }
}

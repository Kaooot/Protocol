package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum EditorWorldType {
  NON_EDITOR,

  EDITOR_PROJECT,

  EDITOR_TEST_LEVEL,

  EDITOR_REALMS_UPLOAD;

  private static final EditorWorldType[] VALUES = values();

  public static EditorWorldType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown EditorWorldType ID: " + ordinal);
  }
}

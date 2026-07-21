package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum ItemDescriptorType {
  EMPTY,

  ITEM_NAME,

  MOLANG,

  ITEM_TAG;

  private static final ItemDescriptorType[] VALUES = values();

  public static ItemDescriptorType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown ItemDescriptorType ID: " + ordinal);
  }
}

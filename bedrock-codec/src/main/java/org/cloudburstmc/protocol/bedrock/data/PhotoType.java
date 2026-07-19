package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum PhotoType {
  /**
   * Portfolio
   */
  PORTFOLIO,

  /**
   * PhotoItem
   */
  PHOTO_ITEM,

  /**
   * Book
   */
  BOOK;

  private static final PhotoType[] VALUES = values();

  public static PhotoType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown PhotoType ID: " + ordinal);
  }
}

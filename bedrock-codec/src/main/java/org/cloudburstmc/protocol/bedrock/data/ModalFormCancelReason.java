package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum ModalFormCancelReason {
  USER_CLOSED,

  USER_BUSY;

  private static final ModalFormCancelReason[] VALUES = values();

  public static ModalFormCancelReason from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown ModalFormCancelReason ID: " + ordinal);
  }
}

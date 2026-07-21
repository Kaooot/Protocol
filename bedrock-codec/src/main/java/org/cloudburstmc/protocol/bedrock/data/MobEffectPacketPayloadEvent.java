package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum MobEffectPacketPayloadEvent {
  INVALID,

  ADD,

  UPDATE,

  REMOVE;

  private static final MobEffectPacketPayloadEvent[] VALUES = values();

  public static MobEffectPacketPayloadEvent from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown MobEffectPacketPayloadEvent ID: " + ordinal);
  }
}

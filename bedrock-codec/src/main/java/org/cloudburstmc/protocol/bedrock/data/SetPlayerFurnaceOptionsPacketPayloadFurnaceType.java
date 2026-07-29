package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum SetPlayerFurnaceOptionsPacketPayloadFurnaceType {
  NONE,

  FURNACE,

  BLAST_FURNACE,

  SMOKER;

  private static final SetPlayerFurnaceOptionsPacketPayloadFurnaceType[] VALUES = values();

  public static SetPlayerFurnaceOptionsPacketPayloadFurnaceType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown SetPlayerFurnaceOptionsPacketPayloadFurnaceType ID: " + ordinal);
  }
}

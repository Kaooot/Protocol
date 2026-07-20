package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum NpcRequestPacketPayloadRequestType {
  SET_ACTIONS,

  EXECUTE_ACTION,

  EXECUTE_CLOSING_COMMANDS,

  SET_NAME,

  SET_SKIN,

  SET_INTERACT_TEXT,

  EXECUTE_OPENING_COMMANDS;

  private static final NpcRequestPacketPayloadRequestType[] VALUES = values();

  public static NpcRequestPacketPayloadRequestType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown NpcRequestPacketPayloadRequestType ID: " + ordinal);
  }
}

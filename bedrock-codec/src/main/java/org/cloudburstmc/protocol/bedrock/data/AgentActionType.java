package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum AgentActionType {
  ATTACK,

  COLLECT,

  DESTROY,

  DETECT_REDSTONE,

  DETECT_OBSTACLE,

  DROP,

  DROP_ALL,

  INSPECT,

  INSPECT_DATA,

  INSPECT_ITEM_COUNT,

  INSPECT_ITEM_DETAIL,

  INSPECT_ITEM_SPACE,

  INTERACT,

  MOVE,

  PLACE_BLOCK,

  TILL,

  TRANSFER_ITEM_TO,

  TURN;

  private static final AgentActionType[] VALUES = values();

  public static AgentActionType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown AgentActionType ID: " + ordinal);
  }
}

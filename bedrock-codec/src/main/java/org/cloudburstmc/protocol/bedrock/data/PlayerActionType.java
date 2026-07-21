package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum PlayerActionType {
  UNKNOWN,

  START_DESTROY_BLOCK,

  ABORT_DESTROY_BLOCK,

  STOP_DESTROY_BLOCK,

  START_SLEEPING,

  STOP_SLEEPING,

  RESPAWN,

  START_JUMP,

  START_SPRINTING,

  STOP_SPRINTING,

  START_SNEAKING,

  STOP_SNEAKING,

  CREATIVE_DESTROY_BLOCK,

  CHANGE_DIMENSION_ACK,

  START_GLIDING,

  STOP_GLIDING,

  DENY_DESTROY_BLOCK,

  CRACK_BLOCK,

  START_SWIMMING,

  STOP_SWIMMING,

  START_SPIN_ATTACK,

  STOP_SPIN_ATTACK,

  PREDICT_DESTROY_BLOCK,

  CONTINUE_DESTROY_BLOCK,

  START_ITEM_USE_ON,

  STOP_ITEM_USE_ON,

  HANDLED_TELEPORT,

  MISSED_SWING,

  START_CRAWLING,

  STOP_CRAWLING,

  START_FLYING,

  STOP_FLYING,

  START_USING_ITEM,

  INTERNAL_UPDATE,

  COUNT;

  private static final PlayerActionType[] VALUES = values();

  public static PlayerActionType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown PlayerActionType ID: " + ordinal);
  }
}

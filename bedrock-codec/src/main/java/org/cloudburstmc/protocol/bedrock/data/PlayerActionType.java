package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum PlayerActionType {
  /**
   * Unknown
   */
  UNKNOWN,

  /**
   * StartDestroyBlock
   */
  START_DESTROY_BLOCK,

  /**
   * AbortDestroyBlock
   */
  ABORT_DESTROY_BLOCK,

  /**
   * StopDestroyBlock
   */
  STOP_DESTROY_BLOCK,

  /**
   * StartSleeping
   */
  START_SLEEPING,

  /**
   * StopSleeping
   */
  STOP_SLEEPING,

  /**
   * Respawn
   */
  RESPAWN,

  /**
   * StartJump
   */
  START_JUMP,

  /**
   * StartSprinting
   */
  START_SPRINTING,

  /**
   * StopSprinting
   */
  STOP_SPRINTING,

  /**
   * StartSneaking
   */
  START_SNEAKING,

  /**
   * StopSneaking
   */
  STOP_SNEAKING,

  /**
   * CreativeDestroyBlock
   */
  CREATIVE_DESTROY_BLOCK,

  /**
   * ChangeDimensionAck
   */
  CHANGE_DIMENSION_ACK,

  /**
   * StartGliding
   */
  START_GLIDING,

  /**
   * StopGliding
   */
  STOP_GLIDING,

  /**
   * DenyDestroyBlock
   */
  DENY_DESTROY_BLOCK,

  /**
   * CrackBlock
   */
  CRACK_BLOCK,

  /**
   * StartSwimming
   */
  START_SWIMMING,

  /**
   * StopSwimming
   */
  STOP_SWIMMING,

  /**
   * StartSpinAttack
   */
  START_SPIN_ATTACK,

  /**
   * StopSpinAttack
   */
  STOP_SPIN_ATTACK,

  /**
   * PredictDestroyBlock
   */
  PREDICT_DESTROY_BLOCK,

  /**
   * ContinueDestroyBlock
   */
  CONTINUE_DESTROY_BLOCK,

  /**
   * StartItemUseOn
   */
  START_ITEM_USE_ON,

  /**
   * StopItemUseOn
   */
  STOP_ITEM_USE_ON,

  /**
   * HandledTeleport
   */
  HANDLED_TELEPORT,

  /**
   * MissedSwing
   */
  MISSED_SWING,

  /**
   * StartCrawling
   */
  START_CRAWLING,

  /**
   * StopCrawling
   */
  STOP_CRAWLING,

  /**
   * StartFlying
   */
  START_FLYING,

  /**
   * StopFlying
   */
  STOP_FLYING,

  /**
   * StartUsingItem
   */
  START_USING_ITEM,

  /**
   * InternalUpdate
   */
  INTERNAL_UPDATE,

  /**
   * Count
   */
  COUNT;

  private static final PlayerActionType[] VALUES = values();

  public static PlayerActionType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown PlayerActionType ID: " + ordinal);
  }
}

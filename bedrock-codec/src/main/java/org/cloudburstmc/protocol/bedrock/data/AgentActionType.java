package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum AgentActionType {
  /**
   * Attack
   */
  ATTACK,

  /**
   * Collect
   */
  COLLECT,

  /**
   * Destroy
   */
  DESTROY,

  /**
   * DetectRedstone
   */
  DETECT_REDSTONE,

  /**
   * DetectObstacle
   */
  DETECT_OBSTACLE,

  /**
   * Drop
   */
  DROP,

  /**
   * DropAll
   */
  DROP_ALL,

  /**
   * Inspect
   */
  INSPECT,

  /**
   * InspectData
   */
  INSPECT_DATA,

  /**
   * InspectItemCount
   */
  INSPECT_ITEM_COUNT,

  /**
   * InspectItemDetail
   */
  INSPECT_ITEM_DETAIL,

  /**
   * InspectItemSpace
   */
  INSPECT_ITEM_SPACE,

  /**
   * Interact
   */
  INTERACT,

  /**
   * Move
   */
  MOVE,

  /**
   * PlaceBlock
   */
  PLACE_BLOCK,

  /**
   * Till
   */
  TILL,

  /**
   * TransferItemTo
   */
  TRANSFER_ITEM_TO,

  /**
   * Turn
   */
  TURN;

  private static final AgentActionType[] VALUES = values();

  public static AgentActionType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown AgentActionType ID: " + ordinal);
  }
}

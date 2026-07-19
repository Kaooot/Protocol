package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
public enum LegacyTelemetryEventPacketPayloadType {
  /**
   * Achievement
   */
  ACHIEVEMENT,

  /**
   * Interaction
   */
  INTERACTION,

  /**
   * PortalCreated
   */
  PORTAL_CREATED,

  /**
   * PortalUsed
   */
  PORTAL_USED,

  /**
   * MobKilled
   */
  MOB_KILLED,

  /**
   * CauldronUsed
   */
  CAULDRON_USED,

  /**
   * PlayerDied
   */
  PLAYER_DIED,

  /**
   * BossKilled
   */
  BOSS_KILLED,

  /**
   * AgentCommand_OBSOLETE
   */
  AGENT_COMMAND_OBSOLETE,

  /**
   * AgentCreated
   */
  AGENT_CREATED,

  /**
   * PatternRemoved_OBSOLETE
   */
  PATTERN_REMOVED_OBSOLETE,

  /**
   * SlashCommand
   */
  SLASH_COMMAND,

  /**
   * FishBucketed_OBSOLETE
   */
  FISH_BUCKETED_OBSOLETE,

  /**
   * MobBorn
   */
  MOB_BORN,

  /**
   * PetDied_OBSOLETE
   */
  PET_DIED_OBSOLETE,

  /**
   * POICauldronUsed
   */
  POICAULDRON_USED,

  /**
   * ComposterUsed
   */
  COMPOSTER_USED,

  /**
   * BellUsed
   */
  BELL_USED,

  /**
   * ActorDefinition
   */
  ACTOR_DEFINITION,

  /**
   * RaidUpdate
   */
  RAID_UPDATE,

  /**
   * PlayerMovementAnomaly_OBSOLETE
   */
  PLAYER_MOVEMENT_ANOMALY_OBSOLETE,

  /**
   * PlayerMovementCorrected_OBSOLETE
   */
  PLAYER_MOVEMENT_CORRECTED_OBSOLETE,

  /**
   * HoneyHarvested
   */
  HONEY_HARVESTED,

  /**
   * TargetBlockHit
   */
  TARGET_BLOCK_HIT,

  /**
   * PiglinBarter
   */
  PIGLIN_BARTER,

  /**
   * PlayerWaxedOrUnwaxedCopper
   */
  PLAYER_WAXED_OR_UNWAXED_COPPER,

  /**
   * CodeBuilderRuntimeAction
   */
  CODE_BUILDER_RUNTIME_ACTION,

  /**
   * CodeBuilderScoreboard
   */
  CODE_BUILDER_SCOREBOARD,

  /**
   * StriderRiddenInLavaInOverworld
   */
  STRIDER_RIDDEN_IN_LAVA_IN_OVERWORLD,

  /**
   * SneakCloseToSculkSensor
   */
  SNEAK_CLOSE_TO_SCULK_SENSOR,

  /**
   * CarefulRestoration
   */
  CAREFUL_RESTORATION,

  /**
   * ItemUsed
   */
  ITEM_USED;

  private static final LegacyTelemetryEventPacketPayloadType[] VALUES = values();

  public static LegacyTelemetryEventPacketPayloadType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown LegacyTelemetryEventPacketPayloadType ID: " + ordinal);
  }
}

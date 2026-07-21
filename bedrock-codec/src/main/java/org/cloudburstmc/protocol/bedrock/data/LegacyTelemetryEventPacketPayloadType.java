package org.cloudburstmc.protocol.bedrock.data;

import java.lang.UnsupportedOperationException;

public enum LegacyTelemetryEventPacketPayloadType {
  ACHIEVEMENT,

  INTERACTION,

  PORTAL_CREATED,

  PORTAL_USED,

  MOB_KILLED,

  CAULDRON_USED,

  PLAYER_DIED,

  BOSS_KILLED,

  AGENT_COMMAND_OBSOLETE,

  AGENT_CREATED,

  PATTERN_REMOVED_OBSOLETE,

  SLASH_COMMAND,

  FISH_BUCKETED_OBSOLETE,

  MOB_BORN,

  PET_DIED_OBSOLETE,

  POICAULDRON_USED,

  COMPOSTER_USED,

  BELL_USED,

  ACTOR_DEFINITION,

  RAID_UPDATE,

  PLAYER_MOVEMENT_ANOMALY_OBSOLETE,

  PLAYER_MOVEMENT_CORRECTED_OBSOLETE,

  HONEY_HARVESTED,

  TARGET_BLOCK_HIT,

  PIGLIN_BARTER,

  PLAYER_WAXED_OR_UNWAXED_COPPER,

  CODE_BUILDER_RUNTIME_ACTION,

  CODE_BUILDER_SCOREBOARD,

  STRIDER_RIDDEN_IN_LAVA_IN_OVERWORLD,

  SNEAK_CLOSE_TO_SCULK_SENSOR,

  CAREFUL_RESTORATION,

  ITEM_USED;

  private static final LegacyTelemetryEventPacketPayloadType[] VALUES = values();

  public static LegacyTelemetryEventPacketPayloadType from(int ordinal) {
    if (ordinal >= 0 && ordinal < VALUES.length) {
      return VALUES[ordinal];
    }
    throw new UnsupportedOperationException("Detected unknown LegacyTelemetryEventPacketPayloadType ID: " + ordinal);
  }
}

package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;

/**
 * Sent as part of the LegacyTelemetryEventPacket, presumably to provide telemetry data upon completion of an achievement
 */
@Data
public class Achievement {
  /**
   * The identifier of the achievement, see MinecraftEventing::AchievementIds
   */
  private AchievementIds achievementID;
}

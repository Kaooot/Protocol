package org.cloudburstmc.protocol.bedrock.data.event;

import java.lang.String;
import lombok.Data;

/**
 * Provides information about an ActorDefinition, used for telemetry purposes
 *
 * @since v388
 */
@Data
public class ActorDefinition {
  /**
   * Event name. Examples:
   * - "minecraft:gain_raid_omen"
   * - "minecraft:clear_add_raid_omen"
   * - "minecraft:trigger_raid"
   * - "minecraft:remove_raid_trigger"
   */
  private String eventName;
}

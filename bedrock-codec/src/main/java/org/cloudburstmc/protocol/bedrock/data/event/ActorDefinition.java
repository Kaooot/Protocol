package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;

/**
 * Provides information about an ActorDefinition, used for telemetry purposes
 */
@Data
@Added("1.13.0")
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
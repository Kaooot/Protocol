package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.event.EventData;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class LegacyTelemetryEventPacket implements BedrockPacket {
    private long targetActorID;
    private boolean usePlayerID;
    private EventData eventData;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    public BedrockPacketType getPacketType() {
        return BedrockPacketType.LEGACY_TELEMETRY_EVENT;
    }

    public enum Type {
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
        POI_CAULDRON_USED,
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

        private static final Type[] VALUES = values();

        public static Type from(int ordinal) {
            if (ordinal >= 0 && ordinal < VALUES.length) {
                return VALUES[ordinal];
            }
            throw new UnsupportedOperationException("Detected unknown LegacyTelemetryEventPacket.Type ID: " + ordinal);
        }
    }

    public enum AgentResult {
        ACTION_FAIL,
        ACTION_SUCCESS,
        QUERY_RESULT_FALSE,
        QUERY_RESULT_TRUE;

        private static final AgentResult[] VALUES = values();

        public static AgentResult from(int ordinal) {
            if (ordinal >= 0 && ordinal < VALUES.length) {
                return VALUES[ordinal];
            }
            throw new UnsupportedOperationException("Detected unknown LegacyTelemetryEventPacket.AgentResult ID: " + ordinal);
        }
    }


    @Override
    public LegacyTelemetryEventPacket clone() {
        try {
            return (LegacyTelemetryEventPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}


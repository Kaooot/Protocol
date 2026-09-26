package org.cloudburstmc.protocol.bedrock.packet;

import lombok.*;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Sends telemetry data to the client.
 * Documentation on the various events can be found in the relevant event classes.
 */
@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class LegacyTelemetryEventPacket implements BedrockPacket {

    /**
     * The ActorUniqueID of the target actor
     */
    private long targetActorID;
    /**
     * The type of the telemetry event
     */
    private LegacyTelemetryEventPacket.Type eventType;
    /**
     * Whether Player ID is used and whether the ActorUniqueID belongs to a player
     */
    private boolean usePlayerID;
    /**
     * The telemetry event
     */
    private Object eventData;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.LEGACY_TELEMETRY_EVENT;
    }

    @Override
    public LegacyTelemetryEventPacket clone() {
        try {
            return (LegacyTelemetryEventPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    @Getter
    @RequiredArgsConstructor
    public enum Type {
        ACHIEVEMENT(0),
        INTERACTION(1),
        PORTAL_CREATED(2),
        PORTAL_USED(3),
        MOB_KILLED(4),
        CAULDRON_USED(5),
        PLAYER_DIED(6),
        BOSS_KILLED(7),
        AGENT_COMMAND_OBSOLETE(-1),
        AGENT_CREATED(-1),
        PATTERN_REMOVED_OBSOLETE(-1),
        SLASH_COMMAND(8),
        FISH_BUCKETED_OBSOLETE(-1),
        MOB_BORN(9),
        PET_DIED_OBSOLETE(-1),
        POICAULDRON_USED(10),
        COMPOSTER_USED(11),
        BELL_USED(12),
        ACTOR_DEFINITION(13),
        RAID_UPDATE(14),
        PLAYER_MOVEMENT_ANOMALY_OBSOLETE(-1),
        PLAYER_MOVEMENT_CORRECTED_OBSOLETE(-1),
        HONEY_HARVESTED(-1),
        TARGET_BLOCK_HIT(15),
        PIGLIN_BARTER(16),
        PLAYER_WAXED_OR_UNWAXED_COPPER(17),
        CODE_BUILDER_RUNTIME_ACTION(18),
        CODE_BUILDER_SCOREBOARD(19),
        STRIDER_RIDDEN_IN_LAVA_IN_OVERWORLD(-1),
        SNEAK_CLOSE_TO_SCULK_SENSOR(-1),
        CAREFUL_RESTORATION(-1),
        ITEM_USED(20);

        private final int newId;

        private static final Type[] VALUES = values();

        public static Type from(int ordinal) {
            if (ordinal >= 0 && ordinal < VALUES.length) {
                return VALUES[ordinal];
            }
            throw new UnsupportedOperationException("Detected unknown LegacyTelemetryEventPacketPayload::Type ID: " + ordinal);
        }
    }
}
package org.cloudburstmc.protocol.bedrock.packet;

import lombok.*;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class PlayerUpdateEntityOverridesPacket implements BedrockPacket {
    private long targetID;
    private int propertyIndex;
    private UpdateType updateType;
    private int intValue;
    private float floatValue;

    @Override
    public PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.PLAYER_UPDATE_ENTITY_OVERRIDES;
    }

    @Override
    public BedrockPacket clone() {
        try {
            return (PlayerUpdateEntityOverridesPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    @Getter
    @RequiredArgsConstructor
    public enum UpdateType {
        CLEAR_OVERRIDES("clearoverrides"),
        REMOVE_OVERRIDE("removeoverride"),
        SET_INT_OVERRIDE("setintoverride"),
        SET_FLOAT_OVERRIDE("setfloatoverride");

        private final String id;

        private static final UpdateType[] VALUES = values();

        public static UpdateType from(String value) {
            for (UpdateType action : VALUES) {
                if (action.getId().equalsIgnoreCase(value)) {
                    return action;
                }
            }
            throw new UnsupportedOperationException("Detected unknown UpdateType ID: " + value);
        }
    }
}
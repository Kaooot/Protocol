package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.ability.AbilitiesIndex;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class RequestAbilityPacket implements BedrockPacket {

    private AbilitiesIndex ability;
    private Type valueType;
    private boolean bool;
    private float floatValue;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.REQUEST_ABILITY;
    }

    @Override
    public RequestAbilityPacket clone() {
        try {
            return (RequestAbilityPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public enum Type {
        UNSET,
        BOOL,
        FLOAT;

        private static final Type[] VALUES = values();

        public static Type from(int ordinal) {
            if (ordinal >= 0 && ordinal < VALUES.length) {
                return VALUES[ordinal];
            }
            throw new UnsupportedOperationException("Detected unknown RequestAbilityPacketPayload::Type ID: " + ordinal);
        }
    }
}
package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class SimpleEventPacket implements BedrockPacket {
    private Subtype type;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SIMPLE_EVENT;
    }

    @Override
    public SimpleEventPacket clone() {
        try {
            return (SimpleEventPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public enum Subtype {
        NONE,
        ENABLE_COMMANDS,
        DISABLE_COMMANDS,
        UNLOCK_WORLD_TEMPLATE_SETTINGS;

        private static final Subtype[] VALUES = values();

        public static Subtype from(int ordinal) {
            if (ordinal >= 0 && ordinal < VALUES.length) {
                return VALUES[ordinal];
            }
            throw new UnsupportedOperationException("Detected unknown SimpleEventPacket.Subtype ID: " + ordinal);
        }
    }
}


package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class ShowCreditsPacket implements BedrockPacket {

    private long playerRuntimeID;
    private CreditsState creditsState;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SHOW_CREDITS;
    }

    @Override
    public ShowCreditsPacket clone() {
        try {
            return (ShowCreditsPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public enum CreditsState {
        START_CREDITS,
        END_CREDITS;

        private static final CreditsState[] VALUES = values();

        public static CreditsState from(int ordinal) {
            if (ordinal >= 0 && ordinal < VALUES.length) {
                return VALUES[ordinal];
            }
            throw new UnsupportedOperationException("Detected unknown CreditsState ID: " + ordinal);
        }
    }
}
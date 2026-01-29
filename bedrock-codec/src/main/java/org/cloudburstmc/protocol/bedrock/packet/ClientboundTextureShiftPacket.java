package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.List;

/**
 * @author Kaooot
 */
@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class ClientboundTextureShiftPacket implements BedrockPacket {

    private Action action;
    private String collectionName;
    private String fromStep;
    private String toStep;
    private List<String> stepList;
    private long currentLengthInTicks;
    private long totalLengthInTicks;
    private boolean enabled;

    @Override
    public PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CLIENTBOUND_TEXTURE_SHIFT;
    }

    @Override
    public ClientboundTextureShiftPacket clone() {
        try {
            return (ClientboundTextureShiftPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public enum Action {
        INVALID,
        INITIALIZE,
        START,
        SET_ENABLED,
        SYNC;

        private static final Action[] VALUES = values();

        public static Action from(int ordinal) {
            if (ordinal >= VALUES.length || ordinal < 0) {
                throw new UnsupportedOperationException("Detected unknown ClientboundTextureShiftPacket.Action ID: " + ordinal);
            }
            return VALUES[ordinal];
        }
    }
}
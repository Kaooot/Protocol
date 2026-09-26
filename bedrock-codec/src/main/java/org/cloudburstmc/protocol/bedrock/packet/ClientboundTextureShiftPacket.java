package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.List;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class ClientboundTextureShiftPacket implements BedrockPacket {

    private Action actionID;
    private String collectionName;
    private String fromStep;
    private String toStep;
    private final List<String> allSteps = new ObjectArrayList<>();
    private long currentLengthInTicks;
    private long totalLengthInTicks;
    private boolean enabled;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
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
            if (ordinal >= 0 && ordinal < VALUES.length) {
                return VALUES[ordinal];
            }
            throw new UnsupportedOperationException("Detected unknown ClientboundTextureShiftPacketPayload::Action ID: " + ordinal);
        }
    }
}
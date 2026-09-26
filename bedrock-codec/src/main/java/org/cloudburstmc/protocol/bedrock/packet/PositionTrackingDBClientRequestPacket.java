package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.positiontracking.PositionTrackingId;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class PositionTrackingDBClientRequestPacket implements BedrockPacket {

    private Action action;
    private PositionTrackingId id;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.POSITION_TRACKING_D_B_CLIENT_REQUEST;
    }

    @Override
    public PositionTrackingDBClientRequestPacket clone() {
        try {
            return (PositionTrackingDBClientRequestPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public enum Action {
        QUERY;

        private static final Action[] VALUES = values();

        public static Action from(int ordinal) {
            if (ordinal >= 0 && ordinal < VALUES.length) {
                return VALUES[ordinal];
            }
            throw new UnsupportedOperationException("Detected unknown PositionTrackingDBClientRequestPacketPayload::Action ID: " + ordinal);
        }
    }
}
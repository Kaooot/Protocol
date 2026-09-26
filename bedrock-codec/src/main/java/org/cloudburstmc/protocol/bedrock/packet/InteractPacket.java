package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class InteractPacket implements BedrockPacket {

    private Action action;
    private long targetRuntimeID;
    private Vector3f position;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.INTERACT;
    }

    @Override
    public InteractPacket clone() {
        try {
            return (InteractPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public enum Action {
        INVALID,
        INTERACT,
        DAMAGE,
        STOP_RIDING,
        INTERACT_UPDATE,
        NPC_OPEN,
        OPEN_INVENTORY;

        private static final Action[] VALUES = values();

        public static Action from(int ordinal) {
            if (ordinal >= 0 && ordinal < VALUES.length) {
                return VALUES[ordinal];
            }
            throw new UnsupportedOperationException("Detected unknown InteractPacketPayload::Action ID: " + ordinal);
        }
    }
}
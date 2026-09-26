package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorSwingSource;
import org.cloudburstmc.protocol.bedrock.data.player.HandSlot;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class AnimatePacket implements BedrockPacket {

    private Action action;
    private long targetActorRuntimeID;
    private float data;
    private ActorSwingSource swingSource;
    private HandSlot hand;
    /**
     * @deprecated since v898
     */
    private float rowingTime;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.ANIMATE;
    }

    @Override
    public AnimatePacket clone() {
        try {
            return (AnimatePacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public enum Action {
        NO_ACTION,
        SWING,
        WAKE_UP,
        CRITICAL_HIT,
        MAGIC_CRITICAL_HIT,
        /**
         * @deprecated since
         */
        ROW_LEFT,
        /**
         * @deprecated since
         */
        ROW_RIGHT;

        private static final Action[] VALUES = values();

        public static Action from(int ordinal) {
            if (ordinal >= 0 && ordinal < VALUES.length) {
                return VALUES[ordinal];
            }
            throw new UnsupportedOperationException("Detected unknown AnimatePacketPayload::Action ID: " + ordinal);
        }
    }
}
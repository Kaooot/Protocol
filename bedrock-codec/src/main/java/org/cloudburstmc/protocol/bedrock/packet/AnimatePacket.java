package org.cloudburstmc.protocol.bedrock.packet;

import lombok.*;
import org.cloudburstmc.protocol.bedrock.data.ActorSwingSource;
import org.cloudburstmc.protocol.bedrock.data.HandSlot;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class AnimatePacket implements BedrockPacket {
    private Action action;
    private long targetRuntimeID;
    /**
     * @since v859
     */
    private float data;
    /**
     * @since v898
     */
    private ActorSwingSource swingSource;
    /**
     * @since v2207
     */
    private HandSlot hand = HandSlot.MAINHAND;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    public BedrockPacketType getPacketType() {
        return BedrockPacketType.ANIMATE;
    }

    public enum Action {
        NO_ACTION,
        SWING,
        WAKE_UP,
        CRITICAL_HIT,
        MAGIC_CRITICAL_HIT,
        /**
         * @deprecated v800 (1.21.80)
         */
        ROW_RIGHT,
        /**
         * @deprecated v800 (1.21.80)
         */
        ROW_LEFT,
    }

    @Override
    public AnimatePacket clone() {
        try {
            return (AnimatePacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
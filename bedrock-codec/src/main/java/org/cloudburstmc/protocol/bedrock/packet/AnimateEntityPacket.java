package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.longs.LongList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class AnimateEntityPacket implements BedrockPacket {

    private String animation;
    private String nextState;
    private String stopExpression;
    /**
     * @since v465
     */
    private int stopExpressionVersion;
    private String controller;
    private float blendOutTime;
    private final LongList runtimeIds = new LongArrayList();

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.ANIMATE_ENTITY;
    }

    @Override
    public AnimateEntityPacket clone() {
        try {
            return (AnimateEntityPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
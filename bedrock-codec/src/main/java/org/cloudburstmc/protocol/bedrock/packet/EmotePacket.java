package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.player.input.EmoteFlag;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.EnumSet;
import java.util.Set;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class EmotePacket implements BedrockPacket {

    private long actorRuntimeId;
    private String emoteId;
    /**
     * @since v729
     */
    private int emoteLengthTicks;
    private String xuid;
    private String platformId;
    private final Set<EmoteFlag> flags = EnumSet.noneOf(EmoteFlag.class);

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.EMOTE;
    }

    @Override
    public EmotePacket clone() {
        try {
            return (EmotePacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.actor.attribute.AttributeData;
import org.cloudburstmc.protocol.bedrock.data.player.PlayerInputTick;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.List;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class UpdateAttributesPacket implements BedrockPacket {

    private long targetRuntimeID;
    private final List<AttributeData> attributeList = new ObjectArrayList<>();
    private PlayerInputTick tick = new PlayerInputTick(0L);

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.UPDATE_ATTRIBUTES;
    }

    @Override
    public UpdateAttributesPacket clone() {
        try {
            return (UpdateAttributesPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
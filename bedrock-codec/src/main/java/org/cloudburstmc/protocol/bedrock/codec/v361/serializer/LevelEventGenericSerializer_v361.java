package org.cloudburstmc.protocol.bedrock.codec.v361.serializer;

import io.netty.buffer.ByteBuf;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.world.event.LevelEventType;
import org.cloudburstmc.protocol.bedrock.packet.LevelEventGenericPacket;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

@RequiredArgsConstructor
public class LevelEventGenericSerializer_v361 implements BedrockPacketSerializer<LevelEventGenericPacket> {

    protected final TypeMap<LevelEventType> typeMap;

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, LevelEventGenericPacket packet) {
        VarInts.writeInt(buffer, typeMap.getId(packet.getEventId()));
        helper.writeTagValue(buffer, packet.getData());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, LevelEventGenericPacket packet) {
        int eventId = VarInts.readInt(buffer);
        packet.setEventId(typeMap.getType(eventId));
        packet.setData(helper.readTagValue(buffer, NbtType.COMPOUND));
    }
}
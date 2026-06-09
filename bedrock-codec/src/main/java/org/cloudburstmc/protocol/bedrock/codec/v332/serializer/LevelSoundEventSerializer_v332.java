package org.cloudburstmc.protocol.bedrock.codec.v332.serializer;

import io.netty.buffer.ByteBuf;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.cloudburstmc.protocol.bedrock.packet.LevelSoundEventPacket;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

@RequiredArgsConstructor
public class LevelSoundEventSerializer_v332 implements BedrockPacketSerializer<LevelSoundEventPacket> {
    private final TypeMap<SoundEvent> typeMap;

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, LevelSoundEventPacket packet) {
        VarInts.writeUnsignedInt(buffer, typeMap.getId(packet.getSoundEvent()));
        helper.writeVector3f(buffer, packet.getPosition());
        VarInts.writeInt(buffer, packet.getData());
        helper.writeString(buffer, packet.getActorIdentifier());
        buffer.writeBoolean(packet.isBaby());
        buffer.writeBoolean(packet.isGlobal());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, LevelSoundEventPacket packet) {
        packet.setSoundEvent(typeMap.getType(VarInts.readUnsignedInt(buffer)));
        packet.setPosition(helper.readVector3f(buffer));
        packet.setData(VarInts.readInt(buffer));
        packet.setActorIdentifier(helper.readString(buffer));
        packet.setBaby(buffer.readBoolean());
        packet.setGlobal(buffer.readBoolean());
    }
}


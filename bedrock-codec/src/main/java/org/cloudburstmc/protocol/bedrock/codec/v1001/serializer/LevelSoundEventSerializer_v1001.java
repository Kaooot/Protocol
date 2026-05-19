package org.cloudburstmc.protocol.bedrock.codec.v1001.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v975.serializer.LevelSoundEventSerializer_v975;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.cloudburstmc.protocol.bedrock.packet.LevelSoundEventPacket;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
public class LevelSoundEventSerializer_v1001 extends LevelSoundEventSerializer_v975 {

    public LevelSoundEventSerializer_v1001(TypeMap<SoundEvent> typeMap) {
        super(typeMap);
    }

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, LevelSoundEventPacket packet) {
        helper.writeString(buffer, packet.getSoundEvent());
        helper.writeVector3f(buffer, packet.getPosition());
        VarInts.writeInt(buffer, packet.getData());
        helper.writeString(buffer, packet.getActorIdentifier());
        buffer.writeBoolean(packet.isBaby());
        buffer.writeBoolean(packet.isGlobal());
        buffer.writeLongLE(packet.getActorUniqueId());
        helper.writeOptionalNull(buffer, packet.getFireAtPosition(), helper::writeVector3f);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, LevelSoundEventPacket packet) {
        packet.setSoundEvent(helper.readString(buffer));
        packet.setPosition(helper.readVector3f(buffer));
        packet.setData(VarInts.readInt(buffer));
        packet.setActorIdentifier(helper.readString(buffer));
        packet.setBaby(buffer.readBoolean());
        packet.setGlobal(buffer.readBoolean());
        packet.setActorUniqueId(buffer.readLongLE());
        packet.setFireAtPosition(helper.readOptional(buffer, null, helper::readVector3f));
    }
}
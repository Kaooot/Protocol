package org.cloudburstmc.protocol.bedrock.codec.v503.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.ChangeMobPropertyPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

public class ChangeMobPropertySerializer_v503 implements BedrockPacketSerializer<ChangeMobPropertyPacket> {

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ChangeMobPropertyPacket packet) {
        VarInts.writeLong(buffer, packet.getActorId());
        helper.writeString(buffer, packet.getPropertyName());
        buffer.writeBoolean(packet.isBoolComponentValue());
        helper.writeString(buffer, packet.getStringComponentValue());
        VarInts.writeInt(buffer, packet.getIntComponentValue());
        buffer.writeFloatLE(packet.getFloatComponentValue());
    }

    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ChangeMobPropertyPacket packet) {
        packet.setActorId(VarInts.readLong(buffer));
        packet.setPropertyName(helper.readString(buffer));
        packet.setBoolComponentValue(buffer.readBoolean());
        packet.setStringComponentValue(helper.readString(buffer));
        packet.setIntComponentValue(VarInts.readInt(buffer));
        packet.setFloatComponentValue(buffer.readFloatLE());
    }
}

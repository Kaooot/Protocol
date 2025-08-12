package org.cloudburstmc.protocol.bedrock.codec.v560.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.UpdateClientInputLocksPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

public class UpdateClientInputLocksSerializer_v560 implements BedrockPacketSerializer<UpdateClientInputLocksPacket> {

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateClientInputLocksPacket packet) {
        VarInts.writeUnsignedInt(buffer, packet.getInputLockComponentData());
        helper.writeVector3f(buffer, packet.getServerPos());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateClientInputLocksPacket packet) {
        packet.setInputLockComponentData(VarInts.readUnsignedInt(buffer));
        packet.setServerPos(helper.readVector3f(buffer));
    }
}

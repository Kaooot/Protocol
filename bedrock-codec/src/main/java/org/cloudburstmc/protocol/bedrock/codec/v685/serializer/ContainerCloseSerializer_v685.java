package org.cloudburstmc.protocol.bedrock.codec.v685.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType;
import org.cloudburstmc.protocol.bedrock.packet.ContainerClosePacket;

public class ContainerCloseSerializer_v685 implements BedrockPacketSerializer<ContainerClosePacket> {
    public static final ContainerCloseSerializer_v685 INSTANCE = new ContainerCloseSerializer_v685();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ContainerClosePacket packet) {
        buffer.writeByte(packet.getContainerID());
        buffer.writeByte(packet.getContainerType().getId());
        buffer.writeBoolean(packet.isServerInitiatedClose());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ContainerClosePacket packet) {
        packet.setContainerID(buffer.readByte());
        packet.setContainerType(ContainerType.from(buffer.readByte()));
        packet.setServerInitiatedClose(buffer.readBoolean());
    }
}

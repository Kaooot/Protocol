package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType;
import org.cloudburstmc.protocol.bedrock.packet.ContainerOpenPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContainerOpenSerializer_v291 implements BedrockPacketSerializer<ContainerOpenPacket> {
    public static final ContainerOpenSerializer_v291 INSTANCE = new ContainerOpenSerializer_v291();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ContainerOpenPacket packet) {
        buffer.writeByte(packet.getContainerID());
        buffer.writeByte(packet.getContainerType().getId());
        helper.writeBlockPosition(buffer, packet.getPosition());
        VarInts.writeLong(buffer, packet.getTargetActorID());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ContainerOpenPacket packet) {
        packet.setContainerID(buffer.readByte());
        packet.setContainerType(ContainerType.from(buffer.readByte()));
        packet.setPosition(helper.readBlockPosition(buffer));
        packet.setTargetActorID(VarInts.readLong(buffer));
    }
}

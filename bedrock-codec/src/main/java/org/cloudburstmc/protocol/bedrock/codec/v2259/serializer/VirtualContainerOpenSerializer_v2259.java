package org.cloudburstmc.protocol.bedrock.codec.v2259.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.VirtualContainerOpenPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VirtualContainerOpenSerializer_v2259 implements BedrockPacketSerializer<VirtualContainerOpenPacket> {
    public static final VirtualContainerOpenSerializer_v2259 INSTANCE = new VirtualContainerOpenSerializer_v2259();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, VirtualContainerOpenPacket packet) {
        buffer.writeByte(packet.getContainerId());
        VarInts.writeUnsignedLong(buffer, packet.getSessionId());
        helper.writeString(buffer, packet.getTitle());
        helper.writeArray(buffer, packet.getSlots(), helper::writeNetworkItemStackDescriptor);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, VirtualContainerOpenPacket packet) {
        packet.setContainerId(buffer.readUnsignedByte());
        packet.setSessionId(VarInts.readUnsignedLong(buffer));
        packet.setTitle(helper.readString(buffer));
        helper.readArray(buffer, packet.getSlots(), helper::readNetworkItemStackDescriptor);
    }
}
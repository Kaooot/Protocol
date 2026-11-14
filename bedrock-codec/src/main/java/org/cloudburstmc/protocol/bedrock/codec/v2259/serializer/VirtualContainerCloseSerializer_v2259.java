package org.cloudburstmc.protocol.bedrock.codec.v2259.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.inventory.VirtualContainerCloseCause;
import org.cloudburstmc.protocol.bedrock.data.inventory.VirtualContainerCloseKind;
import org.cloudburstmc.protocol.bedrock.packet.VirtualContainerClosePacket;
import org.cloudburstmc.protocol.bedrock.packet.VirtualContainerOpenPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VirtualContainerCloseSerializer_v2259 implements BedrockPacketSerializer<VirtualContainerClosePacket> {
    public static final VirtualContainerCloseSerializer_v2259 INSTANCE = new VirtualContainerCloseSerializer_v2259();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, VirtualContainerClosePacket packet) {
        VarInts.writeUnsignedLong(buffer, packet.getSessionId());
        buffer.writeByte(packet.getKind().ordinal());
        buffer.writeByte(packet.getCause().ordinal());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, VirtualContainerClosePacket packet) {
        packet.setSessionId(VarInts.readUnsignedLong(buffer));
        packet.setKind(VirtualContainerCloseKind.from(buffer.readUnsignedByte()));
        packet.setCause(VirtualContainerCloseCause.from(buffer.readUnsignedByte()));
    }
}
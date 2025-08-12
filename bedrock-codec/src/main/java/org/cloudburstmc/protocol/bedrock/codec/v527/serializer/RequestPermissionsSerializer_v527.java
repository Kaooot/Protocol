package org.cloudburstmc.protocol.bedrock.codec.v527.serializer;

import io.netty.buffer.ByteBuf;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.PlayerPermissionLevel;
import org.cloudburstmc.protocol.bedrock.packet.RequestPermissionsPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor
public class RequestPermissionsSerializer_v527 implements BedrockPacketSerializer<RequestPermissionsPacket> {

    private static final PlayerPermissionLevel[] VALUES = PlayerPermissionLevel.values();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, RequestPermissionsPacket packet) {
        buffer.writeLongLE(packet.getTargetPlayerId());
        VarInts.writeInt(buffer, packet.getPlayerPermissionLevel().ordinal());
        buffer.writeShortLE(packet.getCommandPermissionFlags());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, RequestPermissionsPacket packet) {
        packet.setTargetPlayerId(buffer.readLongLE());
        packet.setPlayerPermissionLevel(VALUES[VarInts.readInt(buffer)]);
        packet.setCommandPermissionFlags(buffer.readUnsignedShortLE());
    }
}
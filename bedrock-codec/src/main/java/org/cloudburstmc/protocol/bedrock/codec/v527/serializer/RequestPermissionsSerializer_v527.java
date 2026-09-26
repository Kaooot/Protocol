package org.cloudburstmc.protocol.bedrock.codec.v527.serializer;

import io.netty.buffer.ByteBuf;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.player.PlayerPermissionLevel;
import org.cloudburstmc.protocol.bedrock.packet.RequestPermissionsPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor
public class RequestPermissionsSerializer_v527 implements BedrockPacketSerializer<RequestPermissionsPacket> {

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, RequestPermissionsPacket packet) {
        buffer.writeLongLE(packet.getTargetPlayerIdsRawID());
        VarInts.writeInt(buffer, packet.getPlayerPermissionLevel().ordinal());
        buffer.writeShortLE(packet.getCustomPermissionFlags());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, RequestPermissionsPacket packet) {
        packet.setTargetPlayerIdsRawID(buffer.readLongLE());
        packet.setPlayerPermissionLevel(PlayerPermissionLevel.from(VarInts.readInt(buffer)));
        packet.setCustomPermissionFlags(buffer.readUnsignedShortLE());
    }
}
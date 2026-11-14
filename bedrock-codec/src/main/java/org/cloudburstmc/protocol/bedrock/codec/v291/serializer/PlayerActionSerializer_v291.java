package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.player.input.PlayerActionType;
import org.cloudburstmc.protocol.bedrock.packet.PlayerActionPacket;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

@RequiredArgsConstructor
public class PlayerActionSerializer_v291 implements BedrockPacketSerializer<PlayerActionPacket> {

    private final TypeMap<PlayerActionType> playerActionTypes;

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerActionPacket packet) {
        VarInts.writeUnsignedLong(buffer, packet.getPlayerRuntimeID());
        VarInts.writeInt(buffer, playerActionTypes.getId(packet.getAction()));
        helper.writeBlockPosition(buffer, packet.getBlockPosition());
        VarInts.writeInt(buffer, packet.getFace());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerActionPacket packet) {
        packet.setPlayerRuntimeID(VarInts.readUnsignedLong(buffer));
        packet.setAction(playerActionTypes.getType(VarInts.readInt(buffer)));
        packet.setBlockPosition(helper.readBlockPosition(buffer));
        packet.setFace(VarInts.readInt(buffer));
    }
}
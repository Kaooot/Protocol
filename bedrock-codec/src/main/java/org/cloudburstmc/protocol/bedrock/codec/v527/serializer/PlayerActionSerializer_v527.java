package org.cloudburstmc.protocol.bedrock.codec.v527.serializer;

import io.netty.buffer.ByteBuf;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.PlayerActionType;
import org.cloudburstmc.protocol.bedrock.packet.PlayerActionPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor
public class PlayerActionSerializer_v527 implements BedrockPacketSerializer<PlayerActionPacket> {

    private static final PlayerActionType[] TYPES = PlayerActionType.values();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerActionPacket packet) {
        VarInts.writeUnsignedLong(buffer, packet.getPlayerRuntimeID());
        VarInts.writeInt(buffer, packet.getAction().ordinal());
        helper.writeBlockPosition(buffer, packet.getBlockPosition());
        helper.writeBlockPosition(buffer, packet.getResultPos());
        VarInts.writeInt(buffer, packet.getFace());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerActionPacket packet) {
        packet.setPlayerRuntimeID(VarInts.readUnsignedLong(buffer));
        packet.setAction(TYPES[VarInts.readInt(buffer)]);
        packet.setBlockPosition(helper.readBlockPosition(buffer));
        packet.setResultPos(helper.readBlockPosition(buffer));
        packet.setFace(VarInts.readInt(buffer));
    }
}
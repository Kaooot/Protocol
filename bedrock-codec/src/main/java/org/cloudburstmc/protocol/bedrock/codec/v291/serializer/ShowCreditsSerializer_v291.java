package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.ShowCreditsPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShowCreditsSerializer_v291 implements BedrockPacketSerializer<ShowCreditsPacket> {
    public static final ShowCreditsSerializer_v291 INSTANCE = new ShowCreditsSerializer_v291();


    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ShowCreditsPacket packet) {
        VarInts.writeUnsignedLong(buffer, packet.getPlayerRuntimeID());
        VarInts.writeInt(buffer, packet.getCreditsState().ordinal());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ShowCreditsPacket packet) {
        packet.setPlayerRuntimeID(VarInts.readUnsignedLong(buffer));
        packet.setCreditsState(ShowCreditsPacket.CreditsState.values()[VarInts.readInt(buffer)]);
    }
}

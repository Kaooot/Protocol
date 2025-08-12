package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.TakeItemActorPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TakeItemActorSerializer_v291 implements BedrockPacketSerializer<TakeItemActorPacket> {
    public static final TakeItemActorSerializer_v291 INSTANCE = new TakeItemActorSerializer_v291();


    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, TakeItemActorPacket packet) {
        VarInts.writeUnsignedLong(buffer, packet.getItemRuntimeID());
        VarInts.writeUnsignedLong(buffer, packet.getActorRuntimeID());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, TakeItemActorPacket packet) {
        packet.setItemRuntimeID(VarInts.readUnsignedLong(buffer));
        packet.setActorRuntimeID(VarInts.readUnsignedLong(buffer));
    }
}

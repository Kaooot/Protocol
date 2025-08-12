package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.RemoveActorPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RemoveActorSerializer_v291 implements BedrockPacketSerializer<RemoveActorPacket> {
    public static final RemoveActorSerializer_v291 INSTANCE = new RemoveActorSerializer_v291();


    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, RemoveActorPacket packet) {
        VarInts.writeLong(buffer, packet.getTargetActorID());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, RemoveActorPacket packet) {
        packet.setTargetActorID(VarInts.readLong(buffer));
    }
}

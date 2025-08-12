package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.ActorPickRequestPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ActorPickRequestSerializer_v291 implements BedrockPacketSerializer<ActorPickRequestPacket> {
    public static final ActorPickRequestSerializer_v291 INSTANCE = new ActorPickRequestSerializer_v291();


    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ActorPickRequestPacket packet) {
        buffer.writeLongLE(packet.getActorID());
        buffer.writeByte(packet.getMaxSlots());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ActorPickRequestPacket packet) {
        packet.setActorID(buffer.readLongLE());
        packet.setMaxSlots(buffer.readUnsignedByte());
    }
}

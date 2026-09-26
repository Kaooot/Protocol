package org.cloudburstmc.protocol.bedrock.codec.v898.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.InteractPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InteractSerializer_v898 implements BedrockPacketSerializer<InteractPacket> {

    public static final InteractSerializer_v898 INSTANCE = new InteractSerializer_v898();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, InteractPacket packet) {
        buffer.writeByte(packet.getAction().ordinal());
        VarInts.writeUnsignedLong(buffer, packet.getTargetRuntimeID());
        helper.writeOptionalNull(buffer, packet.getPosition(), helper::writeVector3f);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, InteractPacket packet) {
        packet.setAction(InteractPacket.Action.from(buffer.readUnsignedByte()));
        packet.setTargetRuntimeID(VarInts.readUnsignedLong(buffer));
        packet.setPosition(helper.readOptional(buffer, null, (b, h) -> h.readVector3f(b)));
    }
}
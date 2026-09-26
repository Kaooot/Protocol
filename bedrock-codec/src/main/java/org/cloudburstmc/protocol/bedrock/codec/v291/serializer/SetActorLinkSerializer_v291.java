package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.SetActorLinkPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SetActorLinkSerializer_v291 implements BedrockPacketSerializer<SetActorLinkPacket> {
    public static final SetActorLinkSerializer_v291 INSTANCE = new SetActorLinkSerializer_v291();


    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SetActorLinkPacket packet) {
        helper.writeActorLink(buffer, packet.getLink());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SetActorLinkPacket packet) {
        packet.setLink(helper.readActorLink(buffer));
    }
}

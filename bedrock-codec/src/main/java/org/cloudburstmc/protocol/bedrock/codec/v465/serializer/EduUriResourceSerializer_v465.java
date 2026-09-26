package org.cloudburstmc.protocol.bedrock.codec.v465.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.EduUriResourcePacket;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class EduUriResourceSerializer_v465 implements BedrockPacketSerializer<EduUriResourcePacket> {
    public static final EduUriResourceSerializer_v465 INSTANCE = new EduUriResourceSerializer_v465();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, EduUriResourcePacket packet) {
        helper.writeEduSharedUriResource(buffer, packet.getEduSharedURIResource());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, EduUriResourcePacket packet) {
        packet.setEduSharedURIResource(helper.readEduSharedUriResource(buffer));
    }
}
package org.cloudburstmc.protocol.bedrock.codec.v933.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePacksReadyForValidationPacket;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResourcePacksReadyForValidationSerializer_v933 implements BedrockPacketSerializer<ResourcePacksReadyForValidationPacket> {
    public static final ResourcePacksReadyForValidationSerializer_v933 INSTANCE = new ResourcePacksReadyForValidationSerializer_v933();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePacksReadyForValidationPacket packet) {

    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePacksReadyForValidationPacket packet) {

    }
}
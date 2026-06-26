package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.ResourcePackResponse;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePackClientResponsePacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResourcePackClientResponseSerializer_v291 implements BedrockPacketSerializer<ResourcePackClientResponsePacket> {
    public static final ResourcePackClientResponseSerializer_v291 INSTANCE = new ResourcePackClientResponseSerializer_v291();


    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePackClientResponsePacket packet) {
        buffer.writeByte(packet.getResponse().ordinal() + 1);
        helper.writeArray(buffer, packet.getDownloadingPacks(), ByteBuf::writeShortLE, helper::writeString);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePackClientResponsePacket packet) {
        packet.setResponse(ResourcePackResponse.fromLegacy(buffer.readUnsignedByte()));
        helper.readArray(buffer, packet.getDownloadingPacks(), ByteBuf::readUnsignedShortLE, helper::readString);
    }
}

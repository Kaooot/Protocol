package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.payload.pack.PackInstanceId;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePackStackPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResourcePackStackSerializer_v291 implements BedrockPacketSerializer<ResourcePackStackPacket> {
    public static final ResourcePackStackSerializer_v291 INSTANCE = new ResourcePackStackSerializer_v291();

    protected static final int MAX_LENGTH = 65535;

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePackStackPacket packet) {
        buffer.writeBoolean(packet.isTexturePackRequired());
        helper.writeArray(buffer, packet.getAddonList(), this::writePackInstanceId);
        helper.writeArray(buffer, packet.getTexturePackList(), this::writePackInstanceId);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePackStackPacket packet) {
        packet.setTexturePackRequired(buffer.readBoolean());
        helper.readArray(buffer, packet.getAddonList(), this::readPackInstanceId, MAX_LENGTH);
        helper.readArray(buffer, packet.getTexturePackList(), this::readPackInstanceId, MAX_LENGTH);
    }

    protected void writePackInstanceId(ByteBuf buffer, BedrockCodecHelper helper, PackInstanceId packInstanceId) {
        helper.writeString(buffer, packInstanceId.getPackID());
        helper.writeString(buffer, packInstanceId.getVersion());
        helper.writeString(buffer, packInstanceId.getSubPackName());
    }

    protected PackInstanceId readPackInstanceId(ByteBuf buffer, BedrockCodecHelper helper) {
        final PackInstanceId packInstanceId = new PackInstanceId();
        packInstanceId.setPackID(helper.readString(buffer));
        packInstanceId.setVersion(helper.readString(buffer));
        packInstanceId.setSubPackName(helper.readString(buffer));
        return packInstanceId;
    }
}
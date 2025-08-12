package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePackStackPacket;

import static java.util.Objects.requireNonNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResourcePackStackSerializer_v291 implements BedrockPacketSerializer<ResourcePackStackPacket> {
    public static final ResourcePackStackSerializer_v291 INSTANCE = new ResourcePackStackSerializer_v291();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePackStackPacket packet) {
        buffer.writeBoolean(packet.isTexturePackRequired());
        helper.writeArray(buffer, packet.getAddonList(), this::writeEntry);
        helper.writeArray(buffer, packet.getTexturePackList(), this::writeEntry);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePackStackPacket packet) {
        packet.setTexturePackRequired(buffer.readBoolean());
        helper.readArray(buffer, packet.getAddonList(), this::readEntry);
        helper.readArray(buffer, packet.getTexturePackList(), this::readEntry);
    }

    public ResourcePackStackPacket.Entry readEntry(ByteBuf buffer, BedrockCodecHelper helper) {
        String packId = helper.readString(buffer);
        String packVersion = helper.readString(buffer);
        String subPackName = helper.readString(buffer);
        return new ResourcePackStackPacket.Entry(packId, packVersion, subPackName);
    }

    public void writeEntry(ByteBuf buffer, BedrockCodecHelper helper, ResourcePackStackPacket.Entry entry) {
        requireNonNull(entry, "ResourcePackStackPacket entry is null");

        helper.writeString(buffer, entry.getId());
        helper.writeString(buffer, entry.getVersion());
        helper.writeString(buffer, entry.getSubPackName());
    }
}

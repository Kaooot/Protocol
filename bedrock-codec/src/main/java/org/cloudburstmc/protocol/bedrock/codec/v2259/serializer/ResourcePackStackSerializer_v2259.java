package org.cloudburstmc.protocol.bedrock.codec.v2259.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v898.serializer.ResourcePackStackSerializer_v898;
import org.cloudburstmc.protocol.bedrock.data.resourcepack.EditorPackType;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePackStackPacket;

import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResourcePackStackSerializer_v2259 extends ResourcePackStackSerializer_v898 {
    public static final ResourcePackStackSerializer_v2259 INSTANCE = new ResourcePackStackSerializer_v2259();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePackStackPacket packet) {
        buffer.writeBoolean(packet.isTexturePackRequired());
        helper.writeArray(buffer, packet.getTexturePackList(), this::writePackInstanceId);
        helper.writeString(buffer, packet.getBaseGameVersion());
        helper.writeExperiments(buffer, packet.getExperiments());
        this.serializeEditorPackTypes(buffer, packet.getEditorPackTypes());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePackStackPacket packet) {
        packet.setTexturePackRequired(buffer.readBoolean());
        helper.readArray(buffer, packet.getTexturePackList(), this::readPackInstanceId, MAX_LENGTH);
        packet.setBaseGameVersion(helper.readString(buffer));
        packet.setExperiments(helper.readExperiments(buffer));
        this.deserializeEditorPackTypes(buffer, packet.getEditorPackTypes());
    }

    protected void serializeEditorPackTypes(ByteBuf buffer, Set<EditorPackType> set) {
        int data = 0;
        for (EditorPackType editorPackType : set) {
            data |= editorPackType.ordinal();
        }
        buffer.writeByte(data);
    }

    protected void deserializeEditorPackTypes(ByteBuf buffer, Set<EditorPackType> set) {
        final int data = buffer.readUnsignedByte();
        for (EditorPackType value : EditorPackType.VALUES) {
            if ((data & value.ordinal()) != 0) {
                set.add(value);
            }
        }
    }
}
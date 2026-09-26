package org.cloudburstmc.protocol.bedrock.codec.v361.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.StructureTemplateDataResponsePacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StructureTemplateDataResponseSerializer_v361 implements BedrockPacketSerializer<StructureTemplateDataResponsePacket> {
    public static final StructureTemplateDataResponseSerializer_v361 INSTANCE = new StructureTemplateDataResponseSerializer_v361();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, StructureTemplateDataResponsePacket packet) {
        helper.writeString(buffer, packet.getStructureName());
        NbtMap structuresNbt = packet.getStructuresNbt();
        boolean save = structuresNbt != null;
        buffer.writeBoolean(save);

        if (save) {
            helper.writeTag(buffer, structuresNbt);
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, StructureTemplateDataResponsePacket packet) {
        packet.setStructureName(helper.readString(buffer));

        boolean save = buffer.readBoolean();

        if (save) {
            packet.setStructuresNbt(helper.readTag(buffer, NbtMap.class));
        }
    }
}

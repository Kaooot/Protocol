package org.cloudburstmc.protocol.bedrock.codec.v361.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.structure.StructureTemplateRequestOperation;
import org.cloudburstmc.protocol.bedrock.packet.StructureTemplateDataRequestPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StructureTemplateDataRequestSerializer_v361 implements BedrockPacketSerializer<StructureTemplateDataRequestPacket> {
    public static final StructureTemplateDataRequestSerializer_v361 INSTANCE = new StructureTemplateDataRequestSerializer_v361();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, StructureTemplateDataRequestPacket packet) {
        helper.writeString(buffer, packet.getStructureName());
        helper.writeBlockPosition(buffer, packet.getStructurePosition());
        helper.writeStructureSettings(buffer, packet.getStructureSettings());
        buffer.writeByte(packet.getRequestedOperation().ordinal());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, StructureTemplateDataRequestPacket packet) {
        packet.setStructureName(helper.readStringMaxLen(buffer, 256));
        packet.setStructurePosition(helper.readBlockPosition(buffer));
        packet.setStructureSettings(helper.readStructureSettings(buffer));
        packet.setRequestedOperation(StructureTemplateRequestOperation.from(buffer.readByte()));
    }
}

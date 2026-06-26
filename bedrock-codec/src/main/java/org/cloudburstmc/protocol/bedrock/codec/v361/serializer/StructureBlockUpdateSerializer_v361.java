package org.cloudburstmc.protocol.bedrock.codec.v361.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.payload.structure.StructureBlockType;
import org.cloudburstmc.protocol.bedrock.data.payload.structure.StructureEditorData;
import org.cloudburstmc.protocol.bedrock.data.payload.structure.StructureRedstoneSaveMode;
import org.cloudburstmc.protocol.bedrock.packet.StructureBlockUpdatePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StructureBlockUpdateSerializer_v361 implements BedrockPacketSerializer<StructureBlockUpdatePacket> {
    public static final StructureBlockUpdateSerializer_v361 INSTANCE = new StructureBlockUpdateSerializer_v361();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, StructureBlockUpdatePacket packet) {
        helper.writeBlockPosition(buffer, packet.getBlockPosition());
        this.writeStructureData(buffer, helper, packet.getStructureData());
        buffer.writeBoolean(packet.isTrigger());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, StructureBlockUpdatePacket packet) {
        packet.setBlockPosition(helper.readBlockPosition(buffer));
        packet.setStructureData(this.readStructureData(buffer, helper));
        packet.setTrigger(buffer.readBoolean());
    }

    protected void writeStructureData(ByteBuf buffer, BedrockCodecHelper helper, StructureEditorData data) {
        helper.writeRedactableString(buffer, data.getStructureName());
        helper.writeString(buffer, data.getDataField());
        buffer.writeBoolean(data.isShouldIncludePlayers());
        buffer.writeBoolean(data.isShouldShowBoundingBox());
        VarInts.writeInt(buffer, data.getStructureBlockType().ordinal());
        helper.writeStructureSettings(buffer, data.getStructureSettings());
    }

    protected StructureEditorData readStructureData(ByteBuf buffer, BedrockCodecHelper helper) {
        final StructureEditorData data = new StructureEditorData();
        data.setStructureName(helper.readRedactableString(buffer));
        data.setDataField(helper.readString(buffer));
        data.setShouldIncludePlayers(buffer.readBoolean());
        data.setShouldShowBoundingBox(buffer.readBoolean());
        data.setStructureBlockType(StructureBlockType.from(VarInts.readInt(buffer)));
        data.setStructureSettings(helper.readStructureSettings(buffer));
        data.setRedstoneSaveMode(StructureRedstoneSaveMode.SAVES_TO_DISK);
        return data;
    }
}
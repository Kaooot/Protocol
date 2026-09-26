package org.cloudburstmc.protocol.bedrock.codec.v2168.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v554.serializer.StructureBlockUpdateSerializer_v554;
import org.cloudburstmc.protocol.bedrock.data.structure.StructureBlockType;
import org.cloudburstmc.protocol.bedrock.data.structure.StructureEditorData;
import org.cloudburstmc.protocol.bedrock.data.structure.StructureRedstoneSaveMode;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StructureBlockUpdateSerializer_v2168 extends StructureBlockUpdateSerializer_v554 {
    public static final StructureBlockUpdateSerializer_v2168 INSTANCE = new StructureBlockUpdateSerializer_v2168();

    @Override
    protected void writeStructureData(ByteBuf buffer, BedrockCodecHelper helper, StructureEditorData data) {
        helper.writeRedactableString(buffer, data.getStructureName());
        helper.writeString(buffer, data.getDataField());
        buffer.writeBoolean(data.isShouldIncludePlayers());
        buffer.writeBoolean(data.isShouldShowBoundingBox());
        VarInts.writeInt(buffer, data.getStructureBlockType().ordinal());
        helper.writeStructureSettings(buffer, data.getStructureSettings());
        buffer.writeByte(data.getRedstoneSaveMode().ordinal());
    }

    @Override
    protected StructureEditorData readStructureData(ByteBuf buffer, BedrockCodecHelper helper) {
        final StructureEditorData data = new StructureEditorData();
        data.setStructureName(helper.readRedactableString(buffer));
        data.setDataField(helper.readString(buffer));
        data.setShouldIncludePlayers(buffer.readBoolean());
        data.setShouldShowBoundingBox(buffer.readBoolean());
        data.setStructureBlockType(StructureBlockType.from(VarInts.readInt(buffer)));
        data.setStructureSettings(helper.readStructureSettings(buffer));
        data.setRedstoneSaveMode(StructureRedstoneSaveMode.from(buffer.readUnsignedByte()));
        return data;
    }
}
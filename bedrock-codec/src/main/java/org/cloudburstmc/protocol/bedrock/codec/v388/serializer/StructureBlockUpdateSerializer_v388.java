package org.cloudburstmc.protocol.bedrock.codec.v388.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v361.serializer.StructureBlockUpdateSerializer_v361;
import org.cloudburstmc.protocol.bedrock.data.payload.structure.StructureBlockType;
import org.cloudburstmc.protocol.bedrock.data.payload.structure.StructureEditorData;
import org.cloudburstmc.protocol.bedrock.data.payload.structure.StructureRedstoneSaveMode;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StructureBlockUpdateSerializer_v388 extends StructureBlockUpdateSerializer_v361 {
    public static final StructureBlockUpdateSerializer_v388 INSTANCE = new StructureBlockUpdateSerializer_v388();

    @Override
    protected void writeStructureData(ByteBuf buffer, BedrockCodecHelper helper, StructureEditorData data) {
        super.writeStructureData(buffer, helper, data);
        VarInts.writeInt(buffer, data.getRedstoneSaveMode().ordinal());
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
        data.setRedstoneSaveMode(StructureRedstoneSaveMode.from(VarInts.readInt(buffer)));
        return data;
    }
}
package org.cloudburstmc.protocol.bedrock.codec.v465.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v407.serializer.CraftingDataSerializer_v407;
import org.cloudburstmc.protocol.bedrock.data.payload.crafting.MaterialReducerDataEntry;
import org.cloudburstmc.protocol.bedrock.data.payload.crafting.MaterialReducerEntryOutput;
import org.cloudburstmc.protocol.bedrock.packet.CraftingDataPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CraftingDataSerializer_v465 extends CraftingDataSerializer_v407 {
    public static final CraftingDataSerializer_v465 INSTANCE = new CraftingDataSerializer_v465();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataPacket packet) {
        this.writeEntries(buffer, helper, packet);
        helper.writeArray(buffer, packet.getPotionMixes(), this::writePotionMixDataEntry);
        helper.writeArray(buffer, packet.getContainerMixes(), this::writeContainerMixDataEntry);
        helper.writeArray(buffer, packet.getMaterialReducers(), this::writeMaterialReducerDataEntry);
        buffer.writeBoolean(packet.isClearRecipes());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataPacket packet) {
        this.readEntries(buffer, helper, packet);
        helper.readArray(buffer, packet.getPotionMixes(), this::readPotionMixDataEntry);
        helper.readArray(buffer, packet.getContainerMixes(), this::readContainerMixDataEntry);
        helper.readArray(buffer, packet.getMaterialReducers(), this::readMaterialReducerDataEntry);
        packet.setClearRecipes(buffer.readBoolean());
    }

    protected void writeMaterialReducerDataEntry(ByteBuf buffer, BedrockCodecHelper helper, MaterialReducerDataEntry entry) {
        VarInts.writeInt(buffer, entry.getFromItemKey());
        helper.writeArray(buffer, entry.getItemIdsAndCounts(), this::writeMaterialReducerEntryOutput);
    }

    protected MaterialReducerDataEntry readMaterialReducerDataEntry(ByteBuf buffer, BedrockCodecHelper helper) {
        final MaterialReducerDataEntry entry = new MaterialReducerDataEntry();
        entry.setFromItemKey(VarInts.readInt(buffer));
        helper.readArray(buffer, entry.getItemIdsAndCounts(), this::readMaterialReducerEntryOutput);
        return entry;
    }

    protected void writeMaterialReducerEntryOutput(ByteBuf buffer, BedrockCodecHelper helper, MaterialReducerEntryOutput output) {
        VarInts.writeInt(buffer, output.getItemId());
        VarInts.writeInt(buffer, output.getItemCount());
    }

    protected MaterialReducerEntryOutput readMaterialReducerEntryOutput(ByteBuf buffer, BedrockCodecHelper helper) {
        final MaterialReducerEntryOutput output = new MaterialReducerEntryOutput();
        output.setItemId(VarInts.readInt(buffer));
        output.setItemCount(VarInts.readInt(buffer));
        return output;
    }
}
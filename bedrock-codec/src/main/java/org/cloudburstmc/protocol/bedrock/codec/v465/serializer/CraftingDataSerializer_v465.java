package org.cloudburstmc.protocol.bedrock.codec.v465.serializer;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.v407.serializer.CraftingDataSerializer_v407;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CraftingDataSerializer_v465 extends CraftingDataSerializer_v407 {
    public static final CraftingDataSerializer_v465 INSTANCE = new CraftingDataSerializer_v465();

    /*@Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataPacket packet) {
        helper.writeArray(buffer, packet.getCraftingEntries(), this::writeEntry);
        helper.writeArray(buffer, packet.getPotionMixes(), this::writePotionMixData);
        helper.writeArray(buffer, packet.getContainerMixes(), this::writeContainerMixData);

        helper.writeArray(buffer, packet.getMaterialReducers(), this::writeMaterialReducer); // Addition

        buffer.writeBoolean(packet.isClearRecipes());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataPacket packet) {
        helper.readArray(buffer, packet.getCraftingEntries(), this::readEntry);
        helper.readArray(buffer, packet.getPotionMixes(), this::readPotionMixData);
        helper.readArray(buffer, packet.getContainerMixes(), this::readContainerMixData);

        helper.readArray(buffer, packet.getMaterialReducers(), this::readMaterialReducer); // Addition

        packet.setClearRecipes(buffer.readBoolean());
    }

    protected void writeMaterialReducer(ByteBuf buffer, BedrockCodecHelper helper, MaterialReducerDataEntry reducer) {
        VarInts.writeInt(buffer, reducer.getInputId());
        helper.writeArray(buffer, reducer.getItemIdsAndCounts().object2IntEntrySet(), (buf, entry) -> {
            VarInts.writeInt(buffer, entry.getKey().getRuntimeId());
            VarInts.writeInt(buffer, entry.getIntValue());
        });
    }

    protected MaterialReducerDataEntry readMaterialReducer(ByteBuf buffer, BedrockCodecHelper helper) {
        int inputId = VarInts.readInt(buffer);
        Object2IntMap<ItemDefinition> definitions = new Object2IntOpenHashMap<>();
        int length = VarInts.readUnsignedInt(buffer);
        for (int i = 0; i < length; i++) {
            definitions.put(helper.getItemDefinitions().getDefinition(VarInts.readInt(buffer)), VarInts.readInt(buffer));
        }
        return new MaterialReducerDataEntry(inputId, definitions);
    }*/
}

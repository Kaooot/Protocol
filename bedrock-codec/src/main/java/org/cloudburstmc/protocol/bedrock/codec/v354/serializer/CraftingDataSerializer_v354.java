package org.cloudburstmc.protocol.bedrock.codec.v354.serializer;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.CraftingDataSerializer_v291;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CraftingDataSerializer_v354 extends CraftingDataSerializer_v291 {
    public static final CraftingDataSerializer_v354 INSTANCE = new CraftingDataSerializer_v354();

    /*@Override
    protected ShapelessRecipe readShapelessRecipe(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataEntryType type) {
        List<ItemDescriptorWithCount> inputs = new ObjectArrayList<>();
        helper.readArray(buffer, inputs, buf -> ItemDescriptorWithCount.fromItem(helper.readItem(buf)));

        List<ItemData> outputs = new ObjectArrayList<>();
        helper.readArray(buffer, outputs, helper::readItem);

        UUID uuid = helper.readUuid(buffer);
        String craftingTag = helper.readString(buffer);
        return ShapelessRecipe.of(type, "", inputs, outputs, uuid, craftingTag, 0, -1);
    }

    @Override
    protected void writeShapelessRecipe(ByteBuf buffer, BedrockCodecHelper helper, ShapelessRecipe data) {
        super.writeShapelessRecipe(buffer, helper, data);

        helper.writeString(buffer, data.getRecipeTag());
    }

    @Override
    protected ShapedRecipe readShapedRecipe(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataEntryType type) {
        int width = VarInts.readInt(buffer);
        int height = VarInts.readInt(buffer);
        int inputCount = width * height;
        List<ItemDescriptorWithCount> inputs = new ObjectArrayList<>();
        for (int i = 0; i < inputCount; i++) {
            inputs.add(ItemDescriptorWithCount.fromItem(helper.readItem(buffer)));
        }
        List<ItemData> outputs = new ObjectArrayList<>();
        helper.readArray(buffer, outputs, helper::readItem);
        UUID uuid = helper.readUuid(buffer);
        String craftingTag = helper.readString(buffer);
        return ShapedRecipe.of(type, "", width, height, inputs, outputs, uuid, craftingTag, 0, -1);
    }

    @Override
    protected void writeShapedRecipe(ByteBuf buffer, BedrockCodecHelper helper, ShapedRecipe data) {
        super.writeShapedRecipe(buffer, helper, data);

        helper.writeString(buffer, data.getRecipeTag());
    }

    @Override
    protected FurnaceRecipe readFurnaceRecipe(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataEntryType type) {
        int inputId = VarInts.readInt(buffer);
        int inputData = type == CraftingDataEntryType.FURNACE_AUX_RECIPE ? VarInts.readInt(buffer) : -1;
        ItemData result = helper.readItem(buffer);
        String craftingTag = helper.readString(buffer);
        return FurnaceRecipe.of(type, inputId, inputData, result, craftingTag);
    }

    @Override
    protected void writeFurnaceRecipe(ByteBuf buffer, BedrockCodecHelper helper, FurnaceRecipe data) {
        super.writeFurnaceRecipe(buffer, helper, data);

        helper.writeString(buffer, data.getRecipeTag());
    }*/
}

package org.cloudburstmc.protocol.bedrock.codec.v407.serializer;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.v388.serializer.CraftingDataSerializer_v388;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CraftingDataSerializer_v407 extends CraftingDataSerializer_v388 {
    public static final CraftingDataSerializer_v407 INSTANCE = new CraftingDataSerializer_v407();

    /*@Override
    protected CraftingDataEntry readEntry(ByteBuf buffer, BedrockCodecHelper helper) {
        int typeInt = VarInts.readInt(buffer);
        CraftingDataEntryType type = CraftingDataEntryType.byId(typeInt);

        switch (type) {
            case SHAPELESS_RECIPE:
            case SHAPELESS_CHEMISTRY_RECIPE:
            case USER_DATA_SHAPELESS_RECIPE:
                return this.readShapelessRecipe(buffer, helper, type);
            case SHAPED_RECIPE:
            case SHAPED_CHEMISTRY_RECIPE:
                return this.readShapedRecipe(buffer, helper, type);
            case FURNACE_RECIPE:
                return this.readFurnaceRecipe(buffer, helper, type);
            case FURNACE_AUX_RECIPE:
                return this.readFurnaceDataRecipe(buffer, helper, type);
            case MULTI_RECIPE:
                return this.readMultiRecipe(buffer, helper, type);
            default:
                throw new IllegalArgumentException("Unhandled crafting data type: " + type);
        }
    }

    @Override
    protected void writeEntry(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataEntry craftingData) {
        VarInts.writeInt(buffer, craftingData.getType().ordinal());
        switch (craftingData.getType()) {
            case SHAPELESS_RECIPE:
            case SHAPELESS_CHEMISTRY_RECIPE:
            case USER_DATA_SHAPELESS_RECIPE:
                this.writeShapelessRecipe(buffer, helper, (ShapelessRecipe) craftingData);
                break;
            case SHAPED_RECIPE:
            case SHAPED_CHEMISTRY_RECIPE:
                this.writeShapedRecipe(buffer, helper, (ShapedRecipe) craftingData);
                break;
            case FURNACE_RECIPE:
                this.writeFurnaceRecipe(buffer, helper, (FurnaceRecipe) craftingData);
                break;
            case FURNACE_AUX_RECIPE:
                this.writeFurnaceDataRecipe(buffer, helper, (FurnaceRecipe) craftingData);
                break;
            case MULTI_RECIPE:
                this.writeMultiRecipe(buffer, helper, (MultiRecipe) craftingData);
                break;
        }
    }

    @Override
    protected ShapelessRecipe readShapelessRecipe(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataEntryType type) {
        String recipeId = helper.readString(buffer);
        List<ItemDescriptorWithCount> inputs = new ObjectArrayList<>();
        helper.readArray(buffer, inputs, helper::readIngredient);

        List<ItemData> outputs = new ObjectArrayList<>();
        helper.readArray(buffer, outputs, helper::readItemInstance);

        UUID uuid = helper.readUuid(buffer);
        String craftingTag = helper.readString(buffer);
        int priority = VarInts.readInt(buffer);
        int networkId = VarInts.readUnsignedInt(buffer);
        return ShapelessRecipe.of(type, recipeId, inputs, outputs, uuid, craftingTag, priority, networkId);
    }

    @Override
    protected void writeShapelessRecipe(ByteBuf buffer, BedrockCodecHelper helper, ShapelessRecipe data) {
        helper.writeString(buffer, data.getRecipeUniqueId());
        helper.writeArray(buffer, data.getIngredientList(), helper::writeIngredient);
        helper.writeArray(buffer, data.getProductionList(), helper::writeItemInstance);

        helper.writeUuid(buffer, data.getRecipeID());
        helper.writeString(buffer, data.getRecipeTag());
        VarInts.writeInt(buffer, data.getPriority());
        VarInts.writeUnsignedInt(buffer, data.getNetId());
    }

    @Override
    protected ShapedRecipe readShapedRecipe(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataEntryType type) {
        String recipeId = helper.readString(buffer);
        int width = VarInts.readInt(buffer);
        int height = VarInts.readInt(buffer);
        int inputCount = width * height;
        List<ItemDescriptorWithCount> inputs = new ObjectArrayList<>();
        for (int i = 0; i < inputCount; i++) {
            inputs.add(helper.readIngredient(buffer));
        }
        List<ItemData> outputs = new ObjectArrayList<>();
        helper.readArray(buffer, outputs, helper::readItemInstance);
        UUID uuid = helper.readUuid(buffer);
        String craftingTag = helper.readString(buffer);
        int priority = VarInts.readInt(buffer);
        int networkId = VarInts.readUnsignedInt(buffer);
        return ShapedRecipe.of(type, recipeId, width, height, inputs, outputs, uuid, craftingTag, priority, networkId);
    }

    @Override
    protected void writeShapedRecipe(ByteBuf buffer, BedrockCodecHelper helper, ShapedRecipe data) {
        helper.writeString(buffer, data.getRecipeUniqueId());
        VarInts.writeInt(buffer, data.getRecipeWidth());
        VarInts.writeInt(buffer, data.getRecipeHeight());
        int count = data.getRecipeWidth() * data.getRecipeHeight();
        List<ItemDescriptorWithCount> inputs = data.getIngredientList();
        for (int i = 0; i < count; i++) {
            helper.writeIngredient(buffer, inputs.get(i));
        }
        helper.writeArray(buffer, data.getProductionList(), helper::writeItemInstance);
        helper.writeUuid(buffer, data.getRecipeID());
        helper.writeString(buffer, data.getRecipeTag());
        VarInts.writeInt(buffer, data.getPriority());
        VarInts.writeUnsignedInt(buffer, data.getNetId());
    }

    @Override
    protected FurnaceRecipe readFurnaceRecipe(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataEntryType type) {
        int inputId = VarInts.readInt(buffer);
        ItemData result = helper.readItemInstance(buffer);
        String craftingTag = helper.readString(buffer);
        return FurnaceRecipe.of(type, inputId, -1, result, craftingTag);
    }

    @Override
    protected void writeFurnaceRecipe(ByteBuf buffer, BedrockCodecHelper helper, FurnaceRecipe data) {
        VarInts.writeInt(buffer, data.getInputId());
        helper.writeItemInstance(buffer, data.getResultItem());
        helper.writeString(buffer, data.getRecipeTag());
    }

    protected FurnaceRecipe readFurnaceDataRecipe(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataEntryType type) {
        int inputId = VarInts.readInt(buffer);
        int inputDamage = VarInts.readInt(buffer);
        ItemData result = helper.readItemInstance(buffer);
        String craftingTag = helper.readString(buffer);
        return FurnaceRecipe.of(type, inputId, inputDamage, result, craftingTag);
    }

    protected void writeFurnaceDataRecipe(ByteBuf buffer, BedrockCodecHelper helper, FurnaceRecipe data) {
        VarInts.writeInt(buffer, data.getInputId());
        VarInts.writeInt(buffer, data.getInputData());
        helper.writeItemInstance(buffer, data.getResultItem());
        helper.writeString(buffer, data.getRecipeTag());
    }

    protected MultiRecipe readMultiRecipe(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataEntryType type) {
        UUID uuid = helper.readUuid(buffer);
        int networkId = VarInts.readUnsignedInt(buffer);
        return MultiRecipe.of(uuid, networkId);
    }

    protected void writeMultiRecipe(ByteBuf buffer, BedrockCodecHelper helper, MultiRecipe data) {
        helper.writeUuid(buffer, data.getRecipeID());
        VarInts.writeUnsignedInt(buffer, data.getNetId());
    }

    @Override
    protected PotionMixDataEntry readPotionMixData(ByteBuf buffer, BedrockCodecHelper helper) {
        return new PotionMixDataEntry(
                VarInts.readInt(buffer),
                VarInts.readInt(buffer),
                VarInts.readInt(buffer),
                VarInts.readInt(buffer),
                VarInts.readInt(buffer),
                VarInts.readInt(buffer)
        );
    }

    @Override
    protected void writePotionMixData(ByteBuf buffer, BedrockCodecHelper helper, PotionMixDataEntry data) {
        checkNotNull(data, "data is null");

        VarInts.writeInt(buffer, data.getInputPotionId());
        VarInts.writeInt(buffer, data.getInputPotionItemAux());
        VarInts.writeInt(buffer, data.getReagentItemId());
        VarInts.writeInt(buffer, data.getReagentItemAux());
        VarInts.writeInt(buffer, data.getOutputPotionId());
        VarInts.writeInt(buffer, data.getOutputPotionAux());
    }

    @Override
    protected ContainerMixDataEntry readContainerMixData(ByteBuf buffer, BedrockCodecHelper helper) {
        return new ContainerMixDataEntry(
                VarInts.readInt(buffer),
                VarInts.readInt(buffer),
                VarInts.readInt(buffer)
        );
    }

    @Override
    protected void writeContainerMixData(ByteBuf buffer, BedrockCodecHelper helper, ContainerMixDataEntry data) {
        checkNotNull(data, "data is null");

        VarInts.writeInt(buffer, data.getFromItemId());
        VarInts.writeInt(buffer, data.getReagentItemId());
        VarInts.writeInt(buffer, data.getOutputItemId());
    }*/
}

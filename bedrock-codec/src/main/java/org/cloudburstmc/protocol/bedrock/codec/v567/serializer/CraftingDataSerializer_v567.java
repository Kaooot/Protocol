package org.cloudburstmc.protocol.bedrock.codec.v567.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v465.serializer.CraftingDataSerializer_v465;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.CraftingDataEntryType;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.*;
import org.cloudburstmc.protocol.common.util.VarInts;

public class CraftingDataSerializer_v567 extends CraftingDataSerializer_v465 {

    @Override
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
            case SMITHING_TRANSFORM_RECIPE:
                return this.readSmithingTransformRecipe(buffer, helper, type);
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
            case SMITHING_TRANSFORM_RECIPE:
                this.writeSmithingTransformRecipe(buffer, helper, (SmithingTransformRecipe) craftingData);
                break;
        }
    }

    protected SmithingTransformRecipe readSmithingTransformRecipe(ByteBuf buffer, BedrockCodecHelper helper,
                                                                  CraftingDataEntryType type) {
        return SmithingTransformRecipe.of(
                helper.readString(buffer), // ID
                helper.readIngredient(buffer), // Base
                helper.readIngredient(buffer), // Addition
                helper.readItemInstance(buffer), // Result
                helper.readString(buffer), // Tag
                VarInts.readUnsignedInt(buffer) // Net ID
        );
    }

    protected void writeSmithingTransformRecipe(ByteBuf buffer, BedrockCodecHelper helper,
                                                SmithingTransformRecipe data) {
        helper.writeString(buffer, data.getRecipeUniqueId());
        helper.writeIngredient(buffer, data.getBaseIngredient());
        helper.writeIngredient(buffer, data.getAdditionIngredient());
        helper.writeItemInstance(buffer, data.getResult());
        helper.writeString(buffer, data.getRecipeTag());
        VarInts.writeUnsignedInt(buffer, data.getNetId());
    }
}

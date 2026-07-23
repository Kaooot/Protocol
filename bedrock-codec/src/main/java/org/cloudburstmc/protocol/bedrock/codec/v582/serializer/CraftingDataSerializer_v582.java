package org.cloudburstmc.protocol.bedrock.codec.v582.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v567.serializer.CraftingDataSerializer_v567;
import org.cloudburstmc.protocol.bedrock.data.payload.crafting.CraftingDataEntryType;
import org.cloudburstmc.protocol.bedrock.data.payload.crafting.SmithingTransformRecipePayload;
import org.cloudburstmc.protocol.bedrock.data.payload.crafting.SmithingTrimRecipePayload;
import org.cloudburstmc.protocol.bedrock.packet.CraftingDataPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

public class CraftingDataSerializer_v582 extends CraftingDataSerializer_v567 {

    @Override
    protected void writeEntries(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataPacket packet) {
        super.writeEntries(buffer, helper, packet);

        for (final SmithingTrimRecipePayload payload : packet.getSmithingTrimRecipes()) {
            VarInts.writeInt(buffer, CraftingDataEntryType.SMITHING_TRIM_RECIPE.ordinal());
            this.writeSmithingTrimRecipePayload(buffer, helper, payload);
        }
    }

    @Override
    protected void readEntries(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataPacket packet) {
        final int length = VarInts.readUnsignedInt(buffer);

        for (int i = 0; i < length; i++) {
            final CraftingDataEntryType type = CraftingDataEntryType.byId(VarInts.readInt(buffer));

            switch (type) {
                case SHAPED_RECIPE:
                    packet.getShapedRecipes().add(this.readShapedRecipePayload(buffer, helper));
                    break;
                case SHAPED_CHEMISTRY_RECIPE:
                    packet.getShapedChemistryRecipes().add(this.readShapedRecipePayload(buffer, helper));
                    break;
                case SHAPELESS_RECIPE:
                    packet.getShapelessRecipes().add(this.readShapelessRecipePayload(buffer, helper));
                    break;
                case SHAPELESS_CHEMISTRY_RECIPE:
                    packet.getShapelessChemistryRecipes().add(this.readShapelessRecipePayload(buffer, helper));
                    break;
                case USER_DATA_SHAPELESS_RECIPE:
                    packet.getUserDataShapelessRecipes().add(this.readShapelessRecipePayload(buffer, helper));
                    break;
                case FURNACE_RECIPE:
                case FURNACE_AUX_RECIPE:
                    packet.getFurnaceRecipes().add(this.readFurnaceRecipePayload(buffer, helper, type));
                    break;
                case MULTI:
                    packet.getMultiRecipes().add(this.readMultiRecipePayload(buffer, helper));
                    break;
                case SMITHING_TRANSFORM_RECIPE:
                    packet.getSmithingTransformRecipes().add(this.readSmithingTransformRecipePayload(buffer, helper));
                    break;
                case SMITHING_TRIM_RECIPE:
                    packet.getSmithingTrimRecipes().add(this.readSmithingTrimRecipePayload(buffer, helper));
                    break;
            }
        }
    }

    @Override
    protected void writeSmithingTransformRecipePayload(ByteBuf buffer, BedrockCodecHelper helper, SmithingTransformRecipePayload payload) {
        helper.writeString(buffer, payload.getRecipeId());
        helper.writeIngredient(buffer, payload.getTemplateIngredient());
        helper.writeIngredient(buffer, payload.getBaseIngredient());
        helper.writeIngredient(buffer, payload.getAdditionIngredient());
        helper.writeItemInstance(buffer, payload.getResult());
        helper.writeString(buffer, payload.getTag());
        this.writeRecipeNetId(buffer, payload.getNetId());
    }

    @Override
    protected SmithingTransformRecipePayload readSmithingTransformRecipePayload(ByteBuf buffer, BedrockCodecHelper helper) {
        final SmithingTransformRecipePayload payload = new SmithingTransformRecipePayload();
        payload.setRecipeId(helper.readString(buffer));
        payload.setTemplateIngredient(helper.readIngredient(buffer));
        payload.setBaseIngredient(helper.readIngredient(buffer));
        payload.setAdditionIngredient(helper.readIngredient(buffer));
        payload.setResult(helper.readItemInstance(buffer));
        payload.setTag(helper.readString(buffer));
        payload.setNetId(this.readRecipeNetId(buffer));
        return payload;
    }

    protected void writeSmithingTrimRecipePayload(ByteBuf buffer, BedrockCodecHelper helper, SmithingTrimRecipePayload payload) {
        helper.writeString(buffer, payload.getRecipeId());
        helper.writeIngredient(buffer, payload.getTemplateIngredient());
        helper.writeIngredient(buffer, payload.getBaseIngredient());
        helper.writeIngredient(buffer, payload.getAdditionIngredient());
        helper.writeString(buffer, payload.getTag());
        this.writeRecipeNetId(buffer, payload.getNetId());
    }

    protected SmithingTrimRecipePayload readSmithingTrimRecipePayload(ByteBuf buffer, BedrockCodecHelper helper) {
        final SmithingTrimRecipePayload payload = new SmithingTrimRecipePayload();
        payload.setRecipeId(helper.readString(buffer));
        payload.setTemplateIngredient(helper.readIngredient(buffer));
        payload.setBaseIngredient(helper.readIngredient(buffer));
        payload.setAdditionIngredient(helper.readIngredient(buffer));
        payload.setTag(helper.readString(buffer));
        payload.setNetId(this.readRecipeNetId(buffer));
        return payload;
    }
}
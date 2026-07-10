package org.cloudburstmc.protocol.bedrock.codec.v685.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v671.serializer.CraftingDataSerializer_v671;
import org.cloudburstmc.protocol.bedrock.data.payload.crafting.*;
import org.cloudburstmc.protocol.bedrock.packet.CraftingDataPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

public class CraftingDataSerializer_v685 extends CraftingDataSerializer_v671 {
    public static final CraftingDataSerializer_v685 INSTANCE = new CraftingDataSerializer_v685();

    @Override
    protected void writeEntries(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataPacket packet) {
        final int length = packet.getShapedRecipes().size() +
                packet.getShapedChemistryRecipes().size() +
                packet.getShapelessRecipes().size() +
                packet.getShapelessChemistryRecipes().size() +
                packet.getUserDataShapelessRecipes().size() +
                packet.getMultiRecipes().size() +
                packet.getSmithingTransformRecipes().size() +
                packet.getSmithingTrimRecipes().size();

        VarInts.writeUnsignedInt(buffer, length);

        for (final ShapedRecipePayload payload : packet.getShapedRecipes()) {
            VarInts.writeInt(buffer, CraftingDataEntryType.SHAPED_RECIPE.ordinal());
            this.writeShapedRecipePayload(buffer, helper, payload, CraftingDataEntryType.SHAPED_RECIPE);
        }

        for (final ShapedRecipePayload payload : packet.getShapedChemistryRecipes()) {
            VarInts.writeInt(buffer, CraftingDataEntryType.SHAPED_CHEMISTRY_RECIPE.ordinal());
            this.writeShapedRecipePayload(buffer, helper, payload, CraftingDataEntryType.SHAPED_CHEMISTRY_RECIPE);
        }

        for (final ShapelessRecipePayload payload : packet.getShapelessRecipes()) {
            VarInts.writeInt(buffer, CraftingDataEntryType.SHAPELESS_RECIPE.ordinal());
            this.writeShapelessRecipePayload(buffer, helper, payload, CraftingDataEntryType.SHAPELESS_RECIPE);
        }

        for (final ShapelessRecipePayload payload : packet.getShapelessChemistryRecipes()) {
            VarInts.writeInt(buffer, CraftingDataEntryType.SHAPELESS_CHEMISTRY_RECIPE.ordinal());
            this.writeShapelessRecipePayload(buffer, helper, payload, CraftingDataEntryType.SHAPELESS_CHEMISTRY_RECIPE);
        }

        for (final ShapelessRecipePayload payload : packet.getUserDataShapelessRecipes()) {
            VarInts.writeInt(buffer, CraftingDataEntryType.USER_DATA_SHAPELESS_RECIPE.ordinal());
            this.writeShapelessRecipePayload(buffer, helper, payload, CraftingDataEntryType.USER_DATA_SHAPELESS_RECIPE);
        }

        for (final FurnaceRecipePayload payload : packet.getFurnaceRecipes()) {
            final CraftingDataEntryType type = payload.getAuxValue() != -1 ? CraftingDataEntryType.FURNACE_AUX_RECIPE : CraftingDataEntryType.FURNACE_RECIPE;
            VarInts.writeInt(buffer, type.ordinal());
            this.writeFurnaceRecipePayload(buffer, helper, payload, type);
        }

        for (final MultiRecipePayload multiRecipe : packet.getMultiRecipes()) {
            VarInts.writeInt(buffer, CraftingDataEntryType.MULTI.ordinal());
            this.writeMultiRecipePayload(buffer, helper, multiRecipe);
        }

        for (final SmithingTransformRecipePayload payload : packet.getSmithingTransformRecipes()) {
            VarInts.writeInt(buffer, CraftingDataEntryType.SMITHING_TRANSFORM_RECIPE.ordinal());
            this.writeSmithingTransformRecipePayload(buffer, helper, payload);
        }

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
                    packet.getShapedRecipes().add(this.readShapedRecipePayload(buffer, helper, type));
                    break;
                case SHAPED_CHEMISTRY_RECIPE:
                    packet.getShapedChemistryRecipes().add(this.readShapedRecipePayload(buffer, helper, type));
                    break;
                case SHAPELESS_RECIPE:
                    packet.getShapelessRecipes().add(this.readShapelessRecipePayload(buffer, helper, type));
                    break;
                case SHAPELESS_CHEMISTRY_RECIPE:
                    packet.getShapelessChemistryRecipes().add(this.readShapelessRecipePayload(buffer, helper, type));
                    break;
                case USER_DATA_SHAPELESS_RECIPE:
                    packet.getUserDataShapelessRecipes().add(this.readShapelessRecipePayload(buffer, helper, type));
                    break;
                case FURNACE_RECIPE:
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

    protected void writeShapedRecipePayload(ByteBuf buffer, BedrockCodecHelper helper, ShapedRecipePayload payload, CraftingDataEntryType type) {
        helper.writeString(buffer, payload.getRecipeId());
        VarInts.writeInt(buffer, payload.getWidth());
        VarInts.writeInt(buffer, payload.getHeight());
        final int length = payload.getWidth() * payload.getHeight();
        for (int i = 0; i < length; i++) {
            helper.writeIngredient(buffer, payload.getIngredients().get(i));
        }
        helper.writeArray(buffer, payload.getResults(), helper::writeItemInstance);
        helper.writeUuid(buffer, payload.getUuid());
        helper.writeString(buffer, payload.getTag());
        VarInts.writeInt(buffer, payload.getPriority());
        buffer.writeBoolean(payload.isAssumeSymmetry());
        if (type.equals(CraftingDataEntryType.SHAPED_RECIPE)) {
            this.writeRecipeUnlockingRequirement(buffer, helper, payload.getUnlockingRequirement());
        }
        this.writeRecipeNetId(buffer, payload.getNetId());
    }

    protected ShapedRecipePayload readShapedRecipePayload(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataEntryType type) {
        final ShapedRecipePayload payload = new ShapedRecipePayload();
        payload.setRecipeId(helper.readString(buffer));
        payload.setWidth(VarInts.readInt(buffer));
        payload.setHeight(VarInts.readInt(buffer));
        final int length = payload.getWidth() * payload.getHeight();
        for (int i = 0; i < length; i++) {
            payload.getIngredients().add(helper.readIngredient(buffer));
        }
        helper.readArray(buffer, payload.getResults(), helper::readItemInstance);
        payload.setUuid(helper.readUuid(buffer));
        payload.setTag(helper.readString(buffer));
        payload.setPriority(VarInts.readInt(buffer));
        payload.setAssumeSymmetry(buffer.readBoolean());
        if (type.equals(CraftingDataEntryType.SHAPED_RECIPE)) {
            payload.setUnlockingRequirement(this.readRecipeUnlockingRequirement(buffer, helper));
        }
        payload.setNetId(this.readRecipeNetId(buffer));
        return payload;
    }

    protected void writeShapelessRecipePayload(ByteBuf buffer, BedrockCodecHelper helper, ShapelessRecipePayload payload, CraftingDataEntryType type) {
        helper.writeString(buffer, payload.getRecipeId());
        helper.writeArray(buffer, payload.getIngredients(), helper::writeIngredient);
        helper.writeArray(buffer, payload.getResults(), helper::writeItemInstance);
        helper.writeUuid(buffer, payload.getUuid());
        helper.writeString(buffer, payload.getTag());
        VarInts.writeInt(buffer, payload.getPriority());
        if (type.equals(CraftingDataEntryType.SHAPELESS_RECIPE)) {
            this.writeRecipeUnlockingRequirement(buffer, helper, payload.getUnlockingRequirement());
        }
        this.writeRecipeNetId(buffer, payload.getNetId());
    }

    protected ShapelessRecipePayload readShapelessRecipePayload(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataEntryType type) {
        final ShapelessRecipePayload payload = new ShapelessRecipePayload();
        payload.setRecipeId(helper.readString(buffer));
        helper.readArray(buffer, payload.getIngredients(), helper::readIngredient);
        helper.readArray(buffer, payload.getResults(), helper::readItemInstance);
        payload.setUuid(helper.readUuid(buffer));
        payload.setTag(helper.readString(buffer));
        payload.setPriority(VarInts.readInt(buffer));
        if (type.equals(CraftingDataEntryType.SHAPELESS_RECIPE)) {
            payload.setUnlockingRequirement(this.readRecipeUnlockingRequirement(buffer, helper));
        }
        payload.setNetId(this.readRecipeNetId(buffer));
        return payload;
    }

    protected void writeRecipeUnlockingRequirement(ByteBuf buffer, BedrockCodecHelper helper, RecipeUnlockingRequirement unlockingRequirement) {
        buffer.writeByte(unlockingRequirement.getUnlockingContext().ordinal());
        if (unlockingRequirement.getUnlockingContext().equals(RecipeUnlockingContext.NONE)) {
            helper.writeArray(buffer, unlockingRequirement.getUnlockingIngredients(), helper::writeIngredient);
        }
    }

    protected RecipeUnlockingRequirement readRecipeUnlockingRequirement(ByteBuf buffer, BedrockCodecHelper helper) {
        final RecipeUnlockingContext unlockingContext = RecipeUnlockingContext.from(buffer.readByte());
        final RecipeUnlockingRequirement unlockingRequirement = new RecipeUnlockingRequirement(unlockingContext);
        if (unlockingRequirement.getUnlockingContext().equals(RecipeUnlockingContext.NONE)) {
            helper.readArray(buffer, unlockingRequirement.getUnlockingIngredients(), helper::readIngredient);
        }
        return unlockingRequirement;
    }
}
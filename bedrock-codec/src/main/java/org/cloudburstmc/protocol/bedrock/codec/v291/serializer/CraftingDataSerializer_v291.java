package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.RecipeIngredient;
import org.cloudburstmc.protocol.bedrock.data.payload.crafting.*;
import org.cloudburstmc.protocol.bedrock.packet.CraftingDataPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CraftingDataSerializer_v291 implements BedrockPacketSerializer<CraftingDataPacket> {
    public static final CraftingDataSerializer_v291 INSTANCE = new CraftingDataSerializer_v291();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataPacket packet) {
        this.writeEntries(buffer, helper, packet);
        buffer.writeBoolean(packet.isClearRecipes());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataPacket packet) {
        this.readEntries(buffer, helper, packet);
        packet.setClearRecipes(buffer.readBoolean());
    }

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
            this.writeShapedRecipePayload(buffer, helper, payload);
        }

        for (final ShapedRecipePayload payload : packet.getShapedChemistryRecipes()) {
            VarInts.writeInt(buffer, CraftingDataEntryType.SHAPED_CHEMISTRY_RECIPE.ordinal());
            this.writeShapedRecipePayload(buffer, helper, payload);
        }

        for (final ShapelessRecipePayload payload : packet.getShapelessRecipes()) {
            VarInts.writeInt(buffer, CraftingDataEntryType.SHAPELESS_RECIPE.ordinal());
            this.writeShapelessRecipePayload(buffer, helper, payload);
        }

        for (final ShapelessRecipePayload payload : packet.getShapelessChemistryRecipes()) {
            VarInts.writeInt(buffer, CraftingDataEntryType.SHAPELESS_CHEMISTRY_RECIPE.ordinal());
            this.writeShapelessRecipePayload(buffer, helper, payload);
        }

        for (final ShapelessRecipePayload payload : packet.getUserDataShapelessRecipes()) {
            VarInts.writeInt(buffer, CraftingDataEntryType.USER_DATA_SHAPELESS_RECIPE.ordinal());
            this.writeShapelessRecipePayload(buffer, helper, payload);
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
    }

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
                    packet.getFurnaceRecipes().add(this.readFurnaceRecipePayload(buffer, helper, type));
                    break;
                case MULTI:
                    packet.getMultiRecipes().add(this.readMultiRecipePayload(buffer, helper));
                    break;
            }
        }
    }

    protected void writeShapedRecipePayload(ByteBuf buffer, BedrockCodecHelper helper, ShapedRecipePayload payload) {
        VarInts.writeInt(buffer, payload.getWidth());
        VarInts.writeInt(buffer, payload.getHeight());
        final int length = payload.getWidth() * payload.getHeight();
        for (int i = 0; i < length; i++) {
            helper.writeItem(buffer, payload.getIngredients().get(i).toItem());
        }
        helper.writeArray(buffer, payload.getResults(), helper::writeItem);
        helper.writeUuid(buffer, payload.getUuid());
    }

    protected ShapedRecipePayload readShapedRecipePayload(ByteBuf buffer, BedrockCodecHelper helper) {
        final ShapedRecipePayload payload = new ShapedRecipePayload();
        payload.setWidth(VarInts.readInt(buffer));
        payload.setHeight(VarInts.readInt(buffer));
        helper.readArray(buffer, payload.getIngredients(), (buf, codecHelper) ->
                RecipeIngredient.fromItem(codecHelper.readItem(buf)));
        helper.readArray(buffer, payload.getResults(), helper::readItem);
        payload.setUuid(helper.readUuid(buffer));
        return payload;
    }

    protected void writeShapelessRecipePayload(ByteBuf buffer, BedrockCodecHelper helper, ShapelessRecipePayload payload) {
        helper.writeArray(buffer, payload.getIngredients(), (buf, codecHelper, recipeIngredient) ->
                codecHelper.writeItem(buf, recipeIngredient.toItem()));
        helper.writeArray(buffer, payload.getResults(), helper::writeItem);
        helper.writeUuid(buffer, payload.getUuid());
    }

    protected ShapelessRecipePayload readShapelessRecipePayload(ByteBuf buffer, BedrockCodecHelper helper) {
        final ShapelessRecipePayload payload = new ShapelessRecipePayload();
        helper.readArray(buffer, payload.getIngredients(), (buf, codecHelper) ->
                RecipeIngredient.fromItem(codecHelper.readItem(buf)));
        helper.readArray(buffer, payload.getResults(), helper::readItem);
        payload.setUuid(helper.readUuid(buffer));
        return payload;
    }

    protected void writeFurnaceRecipePayload(ByteBuf buffer, BedrockCodecHelper helper, FurnaceRecipePayload payload, CraftingDataEntryType type) {
        VarInts.writeInt(buffer, payload.getInputId());
        if (type.equals(CraftingDataEntryType.FURNACE_AUX_RECIPE)) {
            VarInts.writeInt(buffer, payload.getAuxValue());
        }
        helper.writeItem(buffer, payload.getResult());
    }

    protected FurnaceRecipePayload readFurnaceRecipePayload(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataEntryType type) {
        final FurnaceRecipePayload payload = new FurnaceRecipePayload();
        payload.setInputId(VarInts.readInt(buffer));
        if (type.equals(CraftingDataEntryType.FURNACE_AUX_RECIPE)) {
            payload.setAuxValue(VarInts.readInt(buffer));
        } else {
            payload.setAuxValue(-1);
        }
        payload.setResult(helper.readItem(buffer));
        return payload;
    }

    protected void writeMultiRecipePayload(ByteBuf buffer, BedrockCodecHelper helper, MultiRecipePayload payload) {
        helper.writeUuid(buffer, payload.getMultiRecipeUUID());
    }

    protected MultiRecipePayload readMultiRecipePayload(ByteBuf buffer, BedrockCodecHelper helper) {
        final MultiRecipePayload payload = new MultiRecipePayload();
        payload.setMultiRecipeUUID(helper.readUuid(buffer));
        return payload;
    }
}
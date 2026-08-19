package org.cloudburstmc.protocol.bedrock.codec.v407.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v388.serializer.CraftingDataSerializer_v388;
import org.cloudburstmc.protocol.bedrock.data.payload.crafting.*;
import org.cloudburstmc.protocol.common.util.VarInts;

import static org.cloudburstmc.protocol.common.util.Preconditions.checkArgument;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CraftingDataSerializer_v407 extends CraftingDataSerializer_v388 {
    public static final CraftingDataSerializer_v407 INSTANCE = new CraftingDataSerializer_v407();

    @Override
    protected void writeShapelessRecipePayload(ByteBuf buffer, BedrockCodecHelper helper, ShapelessRecipePayload payload) {
        helper.writeString(buffer, payload.getRecipeId());
        helper.writeArray(buffer, payload.getIngredients(), helper::writeIngredient);
        helper.writeArray(buffer, payload.getResults(), helper::writeItemInstance);
        helper.writeUuid(buffer, payload.getUuid());
        helper.writeString(buffer, payload.getTag());
        VarInts.writeInt(buffer, payload.getPriority());
        this.writeRecipeNetId(buffer, payload.getNetId());
    }

    @Override
    protected ShapelessRecipePayload readShapelessRecipePayload(ByteBuf buffer, BedrockCodecHelper helper) {
        final ShapelessRecipePayload payload = new ShapelessRecipePayload();
        payload.setRecipeId(helper.readString(buffer));
        helper.readArray(buffer, payload.getIngredients(), helper::readIngredient, MAX_INGREDIENTS);
        helper.readArray(buffer, payload.getResults(), helper::readItemInstance);
        payload.setUuid(helper.readUuid(buffer));
        payload.setTag(helper.readString(buffer));
        payload.setPriority(VarInts.readInt(buffer));
        payload.setNetId(this.readRecipeNetId(buffer));
        return payload;
    }

    @Override
    protected void writeShapedRecipePayload(ByteBuf buffer, BedrockCodecHelper helper, ShapedRecipePayload payload) {
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
        this.writeRecipeNetId(buffer, payload.getNetId());
    }

    @Override
    protected ShapedRecipePayload readShapedRecipePayload(ByteBuf buffer, BedrockCodecHelper helper) {
        final ShapedRecipePayload payload = new ShapedRecipePayload();
        payload.setRecipeId(helper.readString(buffer));
        payload.setWidth(VarInts.readInt(buffer));
        payload.setHeight(VarInts.readInt(buffer));
        final int length = payload.getWidth() * payload.getHeight();
        checkArgument(length <= MAX_INGREDIENTS, "Tried to read %s Ingredients but maximum is %s", length);
        for (int i = 0; i < length; i++) {
            payload.getIngredients().add(helper.readIngredient(buffer));
        }
        helper.readArray(buffer, payload.getResults(), helper::readItemInstance);
        payload.setUuid(helper.readUuid(buffer));
        payload.setTag(helper.readString(buffer));
        payload.setPriority(VarInts.readInt(buffer));
        payload.setNetId(this.readRecipeNetId(buffer));
        return payload;
    }

    @Override
    protected void writeFurnaceRecipePayload(ByteBuf buffer, BedrockCodecHelper helper, FurnaceRecipePayload payload, CraftingDataEntryType type) {
        VarInts.writeInt(buffer, payload.getInputId());
        if (type.equals(CraftingDataEntryType.FURNACE_AUX_RECIPE)) {
            VarInts.writeInt(buffer, payload.getAuxValue());
        }
        helper.writeItemInstance(buffer, payload.getResult());
    }

    @Override
    protected FurnaceRecipePayload readFurnaceRecipePayload(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataEntryType type) {
        final FurnaceRecipePayload payload = new FurnaceRecipePayload();
        payload.setInputId(VarInts.readInt(buffer));
        payload.setAuxValue(type.equals(CraftingDataEntryType.FURNACE_AUX_RECIPE) ? VarInts.readInt(buffer) : -1);
        payload.setResult(helper.readItemInstance(buffer));
        return payload;
    }

    @Override
    protected void writeMultiRecipePayload(ByteBuf buffer, BedrockCodecHelper helper, MultiRecipePayload payload) {
        super.writeMultiRecipePayload(buffer, helper, payload);
        this.writeRecipeNetId(buffer, payload.getNetId());
    }

    @Override
    protected MultiRecipePayload readMultiRecipePayload(ByteBuf buffer, BedrockCodecHelper helper) {
        final MultiRecipePayload payload = super.readMultiRecipePayload(buffer, helper);
        payload.setNetId(this.readRecipeNetId(buffer));
        return payload;
    }

    @Override
    protected void writePotionMixDataEntry(ByteBuf buffer, BedrockCodecHelper helper, PotionMixDataEntry entry) {
        VarInts.writeInt(buffer, entry.getFromPotionId());
        VarInts.writeInt(buffer, entry.getFromItemAux());
        VarInts.writeInt(buffer, entry.getReagentItemId());
        VarInts.writeInt(buffer, entry.getReagentItemAux());
        VarInts.writeInt(buffer, entry.getToPotionId());
        VarInts.writeInt(buffer, entry.getToItemAux());
    }

    @Override
    protected PotionMixDataEntry readPotionMixDataEntry(ByteBuf buffer, BedrockCodecHelper helper) {
        final PotionMixDataEntry entry = new PotionMixDataEntry();
        entry.setFromPotionId(VarInts.readInt(buffer));
        entry.setFromItemAux(VarInts.readInt(buffer));
        entry.setReagentItemId(VarInts.readInt(buffer));
        entry.setReagentItemAux(VarInts.readInt(buffer));
        entry.setToPotionId(VarInts.readInt(buffer));
        entry.setToItemAux(VarInts.readInt(buffer));
        return entry;
    }

    @Override
    protected void writeContainerMixDataEntry(ByteBuf buffer, BedrockCodecHelper helper, ContainerMixDataEntry entry) {
        VarInts.writeInt(buffer, entry.getFromItemId());
        VarInts.writeInt(buffer, entry.getReagentItemId());
        VarInts.writeInt(buffer, entry.getOutputItemId());
    }

    @Override
    protected ContainerMixDataEntry readContainerMixDataEntry(ByteBuf buffer, BedrockCodecHelper helper) {
        final ContainerMixDataEntry entry = new ContainerMixDataEntry();
        entry.setFromItemId(VarInts.readInt(buffer));
        entry.setReagentItemId(VarInts.readInt(buffer));
        entry.setOutputItemId(VarInts.readInt(buffer));
        return entry;
    }

    protected void writeRecipeNetId(ByteBuf buffer, RecipeNetId netId) {
        VarInts.writeUnsignedInt(buffer, netId.getRawId());
    }

    protected RecipeNetId readRecipeNetId(ByteBuf buffer) {
        return new RecipeNetId(VarInts.readUnsignedInt(buffer));
    }
}
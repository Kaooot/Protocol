package org.cloudburstmc.protocol.bedrock.codec.v567.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v465.serializer.CraftingDataSerializer_v465;
import org.cloudburstmc.protocol.bedrock.data.payload.crafting.CraftingDataEntryType;
import org.cloudburstmc.protocol.bedrock.data.payload.crafting.SmithingTransformRecipePayload;
import org.cloudburstmc.protocol.bedrock.packet.CraftingDataPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

public class CraftingDataSerializer_v567 extends CraftingDataSerializer_v465 {

    @Override
    protected void writeSmithingTransformRecipes(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataPacket packet) {
        for (final SmithingTransformRecipePayload payload : packet.getSmithingTransformRecipes()) {
            VarInts.writeInt(buffer, CraftingDataEntryType.SMITHING_TRANSFORM_RECIPE.ordinal());
            this.writeSmithingTransformRecipePayload(buffer, helper, payload);
        }
    }

    @Override
    protected void readSmithingTransformRecipe(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataPacket packet) {
        packet.getSmithingTransformRecipes().add(this.readSmithingTransformRecipePayload(buffer, helper));
    }

    protected void writeSmithingTransformRecipePayload(ByteBuf buffer, BedrockCodecHelper helper, SmithingTransformRecipePayload payload) {
        helper.writeString(buffer, payload.getRecipeId());
        helper.writeIngredient(buffer, payload.getBaseIngredient());
        helper.writeIngredient(buffer, payload.getAdditionIngredient());
        helper.writeItemInstance(buffer, payload.getResult());
        helper.writeString(buffer, payload.getTag());
        this.writeRecipeNetId(buffer, payload.getNetId());
    }

    protected SmithingTransformRecipePayload readSmithingTransformRecipePayload(ByteBuf buffer, BedrockCodecHelper helper) {
        final SmithingTransformRecipePayload payload = new SmithingTransformRecipePayload();
        payload.setRecipeId(helper.readString(buffer));
        payload.setBaseIngredient(helper.readIngredient(buffer));
        payload.setAdditionIngredient(helper.readIngredient(buffer));
        payload.setResult(helper.readItemInstance(buffer));
        payload.setTag(helper.readString(buffer));
        payload.setNetId(this.readRecipeNetId(buffer));
        return payload;
    }

    @Override
    protected int getSize(CraftingDataPacket packet) {
        return super.getSize(packet) + packet.getSmithingTransformRecipes().size();
    }
}
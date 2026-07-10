package org.cloudburstmc.protocol.bedrock.codec.v354.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.CraftingDataSerializer_v291;
import org.cloudburstmc.protocol.bedrock.data.payload.crafting.CraftingDataEntryType;
import org.cloudburstmc.protocol.bedrock.data.payload.crafting.FurnaceRecipePayload;
import org.cloudburstmc.protocol.bedrock.data.payload.crafting.ShapedRecipePayload;
import org.cloudburstmc.protocol.bedrock.data.payload.crafting.ShapelessRecipePayload;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CraftingDataSerializer_v354 extends CraftingDataSerializer_v291 {
    public static final CraftingDataSerializer_v354 INSTANCE = new CraftingDataSerializer_v354();

    @Override
    protected void writeShapedRecipePayload(ByteBuf buffer, BedrockCodecHelper helper, ShapedRecipePayload payload) {
        super.writeShapedRecipePayload(buffer, helper, payload);
        helper.writeString(buffer, payload.getTag());
    }

    @Override
    protected ShapedRecipePayload readShapedRecipePayload(ByteBuf buffer, BedrockCodecHelper helper) {
        final ShapedRecipePayload payload = super.readShapedRecipePayload(buffer, helper);
        payload.setTag(helper.readString(buffer));
        return payload;
    }

    @Override
    protected void writeShapelessRecipePayload(ByteBuf buffer, BedrockCodecHelper helper, ShapelessRecipePayload payload) {
        super.writeShapelessRecipePayload(buffer, helper, payload);
        helper.writeString(buffer, payload.getTag());
    }

    @Override
    protected ShapelessRecipePayload readShapelessRecipePayload(ByteBuf buffer, BedrockCodecHelper helper) {
        final ShapelessRecipePayload payload = super.readShapelessRecipePayload(buffer, helper);
        payload.setTag(helper.readString(buffer));
        return payload;
    }

    @Override
    protected void writeFurnaceRecipePayload(ByteBuf buffer, BedrockCodecHelper helper, FurnaceRecipePayload payload, CraftingDataEntryType type) {
        super.writeFurnaceRecipePayload(buffer, helper, payload, type);
        helper.writeString(buffer, payload.getTag());
    }

    @Override
    protected FurnaceRecipePayload readFurnaceRecipePayload(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataEntryType type) {
        final FurnaceRecipePayload payload = super.readFurnaceRecipePayload(buffer, helper, type);
        payload.setTag(helper.readString(buffer));
        return payload;
    }
}
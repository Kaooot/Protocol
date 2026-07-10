package org.cloudburstmc.protocol.bedrock.codec.v748.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v685.serializer.CraftingDataSerializer_v685;
import org.cloudburstmc.protocol.bedrock.data.payload.crafting.CraftingDataEntryType;
import org.cloudburstmc.protocol.bedrock.data.payload.crafting.ShapelessRecipePayload;
import org.cloudburstmc.protocol.common.util.VarInts;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CraftingDataSerializer_v748 extends CraftingDataSerializer_v685 {
    public static final CraftingDataSerializer_v748 INSTANCE = new CraftingDataSerializer_v748();

    @Override
    protected void writeShapelessRecipePayload(ByteBuf buffer, BedrockCodecHelper helper, ShapelessRecipePayload payload, CraftingDataEntryType type) {
        helper.writeString(buffer, payload.getRecipeId());
        helper.writeArray(buffer, payload.getIngredients(), helper::writeIngredient);
        helper.writeArray(buffer, payload.getResults(), helper::writeItemInstance);
        helper.writeUuid(buffer, payload.getUuid());
        helper.writeString(buffer, payload.getTag());
        VarInts.writeInt(buffer, payload.getPriority());
        if (type.equals(CraftingDataEntryType.SHAPELESS_RECIPE) || type.equals(CraftingDataEntryType.USER_DATA_SHAPELESS_RECIPE)) {
            this.writeRecipeUnlockingRequirement(buffer, helper, payload.getUnlockingRequirement());
        }
        this.writeRecipeNetId(buffer, payload.getNetId());
    }

    @Override
    protected ShapelessRecipePayload readShapelessRecipePayload(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataEntryType type) {
        final ShapelessRecipePayload payload = new ShapelessRecipePayload();
        payload.setRecipeId(helper.readString(buffer));
        helper.readArray(buffer, payload.getIngredients(), helper::readIngredient);
        helper.readArray(buffer, payload.getResults(), helper::readItemInstance);
        payload.setUuid(helper.readUuid(buffer));
        payload.setTag(helper.readString(buffer));
        payload.setPriority(VarInts.readInt(buffer));
        if (type.equals(CraftingDataEntryType.SHAPELESS_RECIPE) || type.equals(CraftingDataEntryType.USER_DATA_SHAPELESS_RECIPE)) {
            payload.setUnlockingRequirement(this.readRecipeUnlockingRequirement(buffer, helper));
        }
        payload.setNetId(this.readRecipeNetId(buffer));
        return payload;
    }
}
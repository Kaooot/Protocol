package org.cloudburstmc.protocol.bedrock.codec.v671.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v582.serializer.CraftingDataSerializer_v582;
import org.cloudburstmc.protocol.bedrock.data.payload.crafting.ShapedRecipePayload;
import org.cloudburstmc.protocol.common.util.VarInts;

import static org.cloudburstmc.protocol.common.util.Preconditions.checkArgument;

public class CraftingDataSerializer_v671 extends CraftingDataSerializer_v582 {
    public static final CraftingDataSerializer_v671 INSTANCE = new CraftingDataSerializer_v671();

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
        buffer.writeBoolean(payload.isAssumeSymmetry());
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
        payload.setAssumeSymmetry(buffer.readBoolean());
        payload.setNetId(this.readRecipeNetId(buffer));
        return payload;
    }
}
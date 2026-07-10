package org.cloudburstmc.protocol.bedrock.codec.v361.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v354.serializer.CraftingDataSerializer_v354;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.EmptyDescriptor;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.NameDescriptor;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.RecipeIngredient;
import org.cloudburstmc.protocol.bedrock.data.payload.crafting.ShapedRecipePayload;
import org.cloudburstmc.protocol.bedrock.data.payload.crafting.ShapelessRecipePayload;
import org.cloudburstmc.protocol.common.util.VarInts;

import static java.util.Objects.requireNonNull;
import static org.cloudburstmc.protocol.common.util.Preconditions.checkArgument;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CraftingDataSerializer_v361 extends CraftingDataSerializer_v354 {
    public static final CraftingDataSerializer_v361 INSTANCE = new CraftingDataSerializer_v361();

    @Override
    protected void writeShapelessRecipePayload(ByteBuf buffer, BedrockCodecHelper helper, ShapelessRecipePayload payload) {
        helper.writeString(buffer, payload.getRecipeId());
        helper.writeArray(buffer, payload.getIngredients(), this::writeIngredient);
        helper.writeArray(buffer, payload.getResults(), helper::writeItem);
        helper.writeUuid(buffer, payload.getUuid());
        helper.writeString(buffer, payload.getTag());
        VarInts.writeInt(buffer, payload.getPriority());
    }

    @Override
    protected ShapelessRecipePayload readShapelessRecipePayload(ByteBuf buffer, BedrockCodecHelper helper) {
        final ShapelessRecipePayload payload = new ShapelessRecipePayload();
        payload.setRecipeId(helper.readString(buffer));
        helper.readArray(buffer, payload.getIngredients(), this::readIngredient);
        helper.readArray(buffer, payload.getResults(), helper::readItem);
        payload.setUuid(helper.readUuid(buffer));
        payload.setTag(helper.readString(buffer));
        payload.setPriority(VarInts.readInt(buffer));
        return payload;
    }

    @Override
    protected void writeShapedRecipePayload(ByteBuf buffer, BedrockCodecHelper helper, ShapedRecipePayload payload) {
        helper.writeString(buffer, payload.getRecipeId());
        VarInts.writeInt(buffer, payload.getWidth());
        VarInts.writeInt(buffer, payload.getHeight());
        final int length = payload.getWidth() * payload.getHeight();
        for (int i = 0; i < length; i++) {
            this.writeIngredient(buffer, payload.getIngredients().get(i));
        }
        helper.writeArray(buffer, payload.getResults(), helper::writeItem);
        helper.writeUuid(buffer, payload.getUuid());
        helper.writeString(buffer, payload.getTag());
        VarInts.writeInt(buffer, payload.getPriority());
    }

    @Override
    protected ShapedRecipePayload readShapedRecipePayload(ByteBuf buffer, BedrockCodecHelper helper) {
        final ShapedRecipePayload payload = new ShapedRecipePayload();
        payload.setRecipeId(helper.readString(buffer));
        payload.setWidth(VarInts.readInt(buffer));
        payload.setHeight(VarInts.readInt(buffer));
        final int length = payload.getWidth() * payload.getHeight();
        for (int i = 0; i < length; i++) {
            payload.getIngredients().add(this.readIngredient(buffer, helper));
        }
        helper.readArray(buffer, payload.getResults(), helper::readItem);
        payload.setUuid(helper.readUuid(buffer));
        payload.setTag(helper.readString(buffer));
        payload.setPriority(VarInts.readInt(buffer));
        return payload;
    }

    protected RecipeIngredient readIngredient(ByteBuf buffer, BedrockCodecHelper helper) {
        int id = VarInts.readInt(buffer);
        ItemDefinition definition = helper.getItemDefinitions().getDefinition(id);

        if (id == 0) {
            return RecipeIngredient.EMPTY;
        } else {
            int auxValue = fromAuxValue(VarInts.readInt(buffer));
            int stackSize = VarInts.readInt(buffer);
            return new RecipeIngredient(new NameDescriptor(definition, auxValue), stackSize);
        }
    }

    protected void writeIngredient(ByteBuf buffer, RecipeIngredient ingredient) {
        requireNonNull(ingredient, "ingredient is null");
        if (ingredient == RecipeIngredient.EMPTY || ingredient.getDescriptor() == EmptyDescriptor.INSTANCE) {
            VarInts.writeInt(buffer, 0);
            return;
        }

        checkArgument(ingredient.getDescriptor() instanceof NameDescriptor, "Descriptor must be of type NameDescriptor");
        NameDescriptor descriptor = (NameDescriptor) ingredient.getDescriptor();

        int id = descriptor.getItemId().getRuntimeId();
        VarInts.writeInt(buffer, id);

        if (id != 0) {
            VarInts.writeInt(buffer, toAuxValue(descriptor.getAuxValue()));
            VarInts.writeInt(buffer, ingredient.getStackSize());
        }
    }

    protected int fromAuxValue(int value) {
        return value == 0x7fff ? -1 : value;
    }

    protected int toAuxValue(int value) {
        return value == -1 ? 0x7fff : value;
    }
}
package org.cloudburstmc.protocol.bedrock.codec.v361.serializer;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.v354.serializer.CraftingDataSerializer_v354;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CraftingDataSerializer_v361 extends CraftingDataSerializer_v354 {
    public static final CraftingDataSerializer_v361 INSTANCE = new CraftingDataSerializer_v361();

   /* @Override
    protected ShapelessRecipe readShapelessRecipe(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataEntryType type) {
        String recipeId = helper.readString(buffer);
        List<ItemDescriptorWithCount> inputs = new ObjectArrayList<>();
        helper.readArray(buffer, inputs, this::readIngredient);

        List<ItemData> outputs = new ObjectArrayList<>();
        helper.readArray(buffer, outputs, helper::readItem);

        UUID uuid = helper.readUuid(buffer);
        String craftingTag = helper.readString(buffer);
        int priority = VarInts.readInt(buffer);
        return ShapelessRecipe.of(type, recipeId, inputs, outputs, uuid, craftingTag, priority, -1);
    }

    @Override
    protected void writeShapelessRecipe(ByteBuf buffer, BedrockCodecHelper helper, ShapelessRecipe data) {
        helper.writeString(buffer, data.getRecipeUniqueId());
        helper.writeArray(buffer, data.getIngredientList(), this::writeIngredient);
        helper.writeArray(buffer, data.getProductionList(), helper::writeItem);
        helper.writeUuid(buffer, data.getRecipeID());
        helper.writeString(buffer, data.getRecipeTag());
        VarInts.writeInt(buffer, data.getPriority());
    }

    @Override
    protected ShapedRecipe readShapedRecipe(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataEntryType type) {
        String recipeId = helper.readString(buffer);
        int width = VarInts.readInt(buffer);
        int height = VarInts.readInt(buffer);
        int inputCount = width * height;
        List<ItemDescriptorWithCount> inputs = new ObjectArrayList<>();
        for (int i = 0; i < inputCount; i++) {
            inputs.add(this.readIngredient(buffer, helper));
        }
        List<ItemData> outputs = new ObjectArrayList<>();
        helper.readArray(buffer, outputs, helper::readItem);
        UUID uuid = helper.readUuid(buffer);
        String craftingTag = helper.readString(buffer);
        int priority = VarInts.readInt(buffer);
        return ShapedRecipe.of(type, recipeId, width, height, inputs, outputs, uuid, craftingTag, priority, -1);
    }

    @Override
    protected void writeShapedRecipe(ByteBuf buffer, BedrockCodecHelper helper, ShapedRecipe data) {
        helper.writeString(buffer, data.getRecipeUniqueId());
        VarInts.writeInt(buffer, data.getRecipeWidth());
        VarInts.writeInt(buffer, data.getRecipeHeight());
        int count = data.getRecipeWidth() * data.getRecipeHeight();
        List<ItemDescriptorWithCount> inputs = data.getIngredientList();
        for (int i = 0; i < count; i++) {
            this.writeIngredient(buffer, inputs.get(i));
        }
        helper.writeArray(buffer, data.getProductionList(), helper::writeItem);
        helper.writeUuid(buffer, data.getRecipeID());
        helper.writeString(buffer, data.getRecipeTag());
        VarInts.writeInt(buffer, data.getPriority());
    }

    protected ItemDescriptorWithCount readIngredient(ByteBuf buffer, BedrockCodecHelper helper) {
        int id = VarInts.readInt(buffer);
        ItemDefinition definition = helper.getItemDefinitions().getDefinition(id);

        if (id == 0) {
            return ItemDescriptorWithCount.EMPTY;
        } else {
            int auxValue = fromAuxValue(VarInts.readInt(buffer));
            int stackSize = VarInts.readInt(buffer);
            return new ItemDescriptorWithCount(new DefaultDescriptor(definition, auxValue), stackSize);
        }
    }

    protected void writeIngredient(ByteBuf buffer, ItemDescriptorWithCount ingredient) {
        requireNonNull(ingredient, "ingredient is null");
        if (ingredient == ItemDescriptorWithCount.EMPTY || ingredient.getDescriptor() == InvalidDescriptor.INSTANCE) {
            VarInts.writeInt(buffer, 0);
            return;
        }

        checkArgument(ingredient.getDescriptor() instanceof DefaultDescriptor, "Descriptor must be of type DefaultDescriptor");
        DefaultDescriptor descriptor = (DefaultDescriptor) ingredient.getDescriptor();

        int id = descriptor.getItemId().getRuntimeId();
        VarInts.writeInt(buffer, id);

        if (id != 0) {
            VarInts.writeInt(buffer, toAuxValue(descriptor.getAuxValue()));
            VarInts.writeInt(buffer, ingredient.getCount());
        }
    }

    protected int fromAuxValue(int value) {
        return value == 0x7fff ? -1 : value;
    }

    protected int toAuxValue(int value) {
        return value == -1 ? 0x7fff : value;
    }*/
}

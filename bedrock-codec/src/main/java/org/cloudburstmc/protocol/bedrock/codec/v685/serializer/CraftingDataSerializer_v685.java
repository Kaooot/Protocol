package org.cloudburstmc.protocol.bedrock.codec.v685.serializer;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import java.util.UUID;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v671.serializer.CraftingDataSerializer_v671;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.CraftingDataEntryType;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.RecipeUnlockingRequirement;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.GridCraftingDataEntry;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.ShapedRecipe;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.ShapelessRecipe;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount;
import org.cloudburstmc.protocol.common.util.VarInts;

public class CraftingDataSerializer_v685 extends CraftingDataSerializer_v671 {
    public static final CraftingDataSerializer_v685 INSTANCE = new CraftingDataSerializer_v685();

    @Override
    protected ShapelessRecipe readShapelessRecipe(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataEntryType type) {
        String recipeId = helper.readString(buffer);
        List<ItemDescriptorWithCount> inputs = new ObjectArrayList<>();
        helper.readArray(buffer, inputs, helper::readIngredient);

        List<ItemData> outputs = new ObjectArrayList<>();
        helper.readArray(buffer, outputs, helper::readItemInstance);

        UUID uuid = helper.readUuid(buffer);
        String craftingTag = helper.readString(buffer);
        int priority = VarInts.readInt(buffer);

        RecipeUnlockingRequirement requirement = RecipeUnlockingRequirement.INVALID;
        if (type == CraftingDataEntryType.SHAPELESS_RECIPE) {
            requirement = this.readRequirement(buffer, helper, type);
        }

        int networkId = VarInts.readUnsignedInt(buffer);
        return ShapelessRecipe.of(type, recipeId, inputs, outputs, uuid, craftingTag, priority, networkId, requirement);
    }

    @Override
    protected void writeShapelessRecipe(ByteBuf buffer, BedrockCodecHelper helper, ShapelessRecipe data) {
        helper.writeString(buffer, data.getRecipeUniqueId());
        helper.writeArray(buffer, data.getIngredientList(), helper::writeIngredient);
        helper.writeArray(buffer, data.getProductionList(), helper::writeItemInstance);

        helper.writeUuid(buffer, data.getRecipeID());
        helper.writeString(buffer, data.getRecipeTag());
        VarInts.writeInt(buffer, data.getPriority());

        if (data.getType() == CraftingDataEntryType.SHAPELESS_RECIPE) {
            this.writeRequirement(buffer, helper, data);
        }
        VarInts.writeUnsignedInt(buffer, data.getNetId());
    }

    @Override
    protected ShapedRecipe readShapedRecipe(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataEntryType type) {
        String recipeId = helper.readString(buffer);
        int width = VarInts.readInt(buffer);
        int height = VarInts.readInt(buffer);
        int inputCount = width * height;
        List<ItemDescriptorWithCount> inputs = new ObjectArrayList<>(inputCount);
        for (int i = 0; i < inputCount; i++) {
            inputs.add(helper.readIngredient(buffer));
        }
        List<ItemData> outputs = new ObjectArrayList<>();
        helper.readArray(buffer, outputs, helper::readItemInstance);
        UUID uuid = helper.readUuid(buffer);
        String craftingTag = helper.readString(buffer);
        int priority = VarInts.readInt(buffer);
        boolean assumeSymmetry = buffer.readBoolean();

        RecipeUnlockingRequirement requirement = RecipeUnlockingRequirement.INVALID;
        if (type == CraftingDataEntryType.SHAPED_RECIPE) {
            requirement = this.readRequirement(buffer, helper, type);
        }

        int networkId = VarInts.readUnsignedInt(buffer);
        return ShapedRecipe.of(type, recipeId, width, height, inputs, outputs, uuid, craftingTag, priority, networkId, assumeSymmetry, requirement);
    }

    @Override
    protected void writeShapedRecipe(ByteBuf buffer, BedrockCodecHelper helper, ShapedRecipe data) {
        helper.writeString(buffer, data.getRecipeUniqueId());
        VarInts.writeInt(buffer, data.getRecipeWidth());
        VarInts.writeInt(buffer, data.getRecipeHeight());
        int count = data.getRecipeWidth() * data.getRecipeHeight();
        List<ItemDescriptorWithCount> inputs = data.getIngredientList();
        for (int i = 0; i < count; i++) {
            helper.writeIngredient(buffer, inputs.get(i));
        }
        helper.writeArray(buffer, data.getProductionList(), helper::writeItemInstance);
        helper.writeUuid(buffer, data.getRecipeID());
        helper.writeString(buffer, data.getRecipeTag());
        VarInts.writeInt(buffer, data.getPriority());
        buffer.writeBoolean(data.isAssumeSymmetry());

        if (data.getType() == CraftingDataEntryType.SHAPED_RECIPE) {
            this.writeRequirement(buffer, helper, data);
        }

        VarInts.writeUnsignedInt(buffer, data.getNetId());
    }

    protected RecipeUnlockingRequirement readRequirement(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataEntryType type) {
        RecipeUnlockingRequirement requirement = new RecipeUnlockingRequirement(RecipeUnlockingRequirement.UnlockingContext.from(buffer.readByte()));
        if (requirement.getContext().equals(RecipeUnlockingRequirement.UnlockingContext.NONE)) {
            helper.readArray(buffer, requirement.getIngredients(), (buf, h) -> h.readIngredient(buf));
        }
        return requirement;
    }

    protected void writeRequirement(ByteBuf buffer, BedrockCodecHelper helper, GridCraftingDataEntry data) {
        buffer.writeByte(data.getUnlockingRequirement().getContext().ordinal());
        if (data.getUnlockingRequirement().getContext().equals(RecipeUnlockingRequirement.UnlockingContext.NONE)) {
            helper.writeArray(buffer, data.getUnlockingRequirement().getIngredients(), (buf, h, ingredient) -> h.writeIngredient(buf, ingredient));
        }
    }
}
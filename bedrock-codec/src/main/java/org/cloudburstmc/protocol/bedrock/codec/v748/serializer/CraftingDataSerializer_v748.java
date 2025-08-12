package org.cloudburstmc.protocol.bedrock.codec.v748.serializer;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v685.serializer.CraftingDataSerializer_v685;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.CraftingDataEntryType;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.RecipeUnlockingRequirement;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.ShapelessRecipe;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CraftingDataSerializer_v748 extends CraftingDataSerializer_v685 {
    public static final CraftingDataSerializer_v748 INSTANCE = new CraftingDataSerializer_v748();

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
        if (type == CraftingDataEntryType.SHAPELESS_RECIPE || type == CraftingDataEntryType.USER_DATA_SHAPELESS_RECIPE) {
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

        if (data.getType() == CraftingDataEntryType.SHAPELESS_RECIPE || data.getType() == CraftingDataEntryType.USER_DATA_SHAPELESS_RECIPE) {
            this.writeRequirement(buffer, helper, data);
        }
        VarInts.writeUnsignedInt(buffer, data.getNetId());
    }
}
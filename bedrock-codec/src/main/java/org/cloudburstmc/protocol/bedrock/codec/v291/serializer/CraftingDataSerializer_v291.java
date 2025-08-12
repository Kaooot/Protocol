package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.CraftingDataEntryType;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.*;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount;
import org.cloudburstmc.protocol.bedrock.packet.CraftingDataPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CraftingDataSerializer_v291 implements BedrockPacketSerializer<CraftingDataPacket> {
    public static final CraftingDataSerializer_v291 INSTANCE = new CraftingDataSerializer_v291();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataPacket packet) {
        helper.writeArray(buffer, packet.getCraftingEntries(), this::writeEntry);
        buffer.writeBoolean(packet.isClearRecipes());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataPacket packet) {
        helper.readArray(buffer, packet.getCraftingEntries(), this::readEntry);
        packet.setClearRecipes(buffer.readBoolean());
    }

    protected CraftingDataEntry readEntry(ByteBuf buffer, BedrockCodecHelper helper) {
        int typeInt = VarInts.readInt(buffer);
        CraftingDataEntryType type = CraftingDataEntryType.byId(typeInt);

        switch (type) {
            case SHAPELESS_RECIPE:
            case SHAPELESS_CHEMISTRY_RECIPE:
            case USER_DATA_SHAPELESS_RECIPE:
                return this.readShapelessRecipe(buffer, helper, type);
            case SHAPED_RECIPE:
            case SHAPED_CHEMISTRY_RECIPE:
                return this.readShapedRecipe(buffer, helper, type);
            case FURNACE_RECIPE:
            case FURNACE_AUX_RECIPE:
                return this.readFurnaceRecipe(buffer, helper, type);
            case MULTI_RECIPE:
                return this.readMultiRecipe(buffer, helper, type);
            default:
                throw new IllegalArgumentException("Unhandled crafting data type: " + type);
        }
    }

    protected void writeEntry(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataEntry craftingData) {
        VarInts.writeInt(buffer, craftingData.getType().ordinal());
        switch (craftingData.getType()) {
            case SHAPELESS_RECIPE:
            case SHAPELESS_CHEMISTRY_RECIPE:
            case USER_DATA_SHAPELESS_RECIPE:
                this.writeShapelessRecipe(buffer, helper, (ShapelessRecipe) craftingData);
                break;
            case SHAPED_RECIPE:
            case SHAPED_CHEMISTRY_RECIPE:
                this.writeShapedRecipe(buffer, helper, (ShapedRecipe) craftingData);
                break;
            case FURNACE_RECIPE:
            case FURNACE_AUX_RECIPE:
                this.writeFurnaceRecipe(buffer, helper, (FurnaceRecipe) craftingData);
                break;
            case MULTI_RECIPE:
                this.writeMultiRecipe(buffer, helper, (MultiRecipe) craftingData);
                break;
        }
    }

    protected ShapelessRecipe readShapelessRecipe(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataEntryType type) {
        List<ItemDescriptorWithCount> inputs = new ObjectArrayList<>();
        helper.readArray(buffer, inputs, buf -> ItemDescriptorWithCount.fromItem(helper.readItem(buf)));

        List<ItemData> outputs = new ObjectArrayList<>();
        helper.readArray(buffer, outputs, helper::readItem);

        UUID uuid = helper.readUuid(buffer);
        return ShapelessRecipe.of(type, "", inputs, outputs, uuid, "", 0, -1);
    }

    protected void writeShapelessRecipe(ByteBuf buffer, BedrockCodecHelper helper, ShapelessRecipe data) {
        helper.writeArray(buffer, data.getIngredientList(), (buf, item) -> helper.writeItem(buf, item.toItem()));
        helper.writeArray(buffer, data.getProductionList(), helper::writeItem);
        helper.writeUuid(buffer, data.getRecipeID());
    }

    protected ShapedRecipe readShapedRecipe(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataEntryType type) {
        int width = VarInts.readInt(buffer);
        int height = VarInts.readInt(buffer);
        int inputCount = width * height;
        List<ItemDescriptorWithCount> inputs = new ObjectArrayList<>();
        for (int i = 0; i < inputCount; i++) {
            inputs.add(ItemDescriptorWithCount.fromItem(helper.readItem(buffer)));
        }
        List<ItemData> outputs = new ObjectArrayList<>();
        helper.readArray(buffer, outputs, helper::readItem);
        UUID uuid = helper.readUuid(buffer);
        return ShapedRecipe.of(type, "", width, height, inputs, outputs, uuid, "", 0, -1);
    }

    protected void writeShapedRecipe(ByteBuf buffer, BedrockCodecHelper helper, ShapedRecipe data) {
        VarInts.writeInt(buffer, data.getRecipeWidth());
        VarInts.writeInt(buffer, data.getRecipeHeight());
        int count = data.getRecipeWidth() * data.getRecipeHeight();
        List<ItemDescriptorWithCount> inputs = data.getIngredientList();
        for (int i = 0; i < count; i++) {
            helper.writeItem(buffer, inputs.get(i).toItem());
        }
        helper.writeArray(buffer, data.getProductionList(), helper::writeItem);
        helper.writeUuid(buffer, data.getRecipeID());
    }

    protected FurnaceRecipe readFurnaceRecipe(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataEntryType type) {
        int inputId = VarInts.readInt(buffer);
        int inputDamage = type == CraftingDataEntryType.FURNACE_AUX_RECIPE ? VarInts.readInt(buffer) : -1;
        ItemData result = helper.readItem(buffer);
        return FurnaceRecipe.of(type, inputId, inputDamage, result, "");
    }

    protected void writeFurnaceRecipe(ByteBuf buffer, BedrockCodecHelper helper, FurnaceRecipe data) {
        VarInts.writeInt(buffer, data.getInputId());
        if (data.getType() == CraftingDataEntryType.FURNACE_AUX_RECIPE) {
            VarInts.writeInt(buffer, data.getInputData());
        }
        helper.writeItem(buffer, data.getResultItem());
    }

    protected MultiRecipe readMultiRecipe(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataEntryType type) {
        UUID uuid = helper.readUuid(buffer);
        return MultiRecipe.of(uuid, -1);
    }

    protected void writeMultiRecipe(ByteBuf buffer, BedrockCodecHelper helper, MultiRecipe data) {
        helper.writeUuid(buffer, data.getRecipeID());
    }
}

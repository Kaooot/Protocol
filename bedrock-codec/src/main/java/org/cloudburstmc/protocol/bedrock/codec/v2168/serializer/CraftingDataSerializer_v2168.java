package org.cloudburstmc.protocol.bedrock.codec.v2168.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.*;
import org.cloudburstmc.protocol.bedrock.data.payload.crafting.*;
import org.cloudburstmc.protocol.bedrock.packet.CraftingDataPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CraftingDataSerializer_v2168 implements BedrockPacketSerializer<CraftingDataPacket> {
    public static final CraftingDataSerializer_v2168 INSTANCE = new CraftingDataSerializer_v2168();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataPacket packet) {
        helper.writeArray(buffer, packet.getShapedRecipes(), this::writeShapedRecipePayload);
        helper.writeArray(buffer, packet.getShapelessRecipes(), this::writeShapelessRecipePayload);
        helper.writeArray(buffer, packet.getMultiRecipes(), this::writeMultiRecipePayload);
        helper.writeArray(buffer, packet.getUserDataShapelessRecipes(), this::writeShapelessRecipePayload);
        helper.writeArray(buffer, packet.getShapelessChemistryRecipes(), this::writeShapelessRecipePayload);
        helper.writeArray(buffer, packet.getShapedChemistryRecipes(), this::writeShapedRecipePayload);
        helper.writeArray(buffer, packet.getSmithingTransformRecipes(), this::writeSmithingTransformRecipePayload);
        helper.writeArray(buffer, packet.getSmithingTrimRecipes(), this::writeSmithingTrimRecipePayload);
        helper.writeArray(buffer, packet.getPotionMixes(), this::writePotionMixDataEntry);
        helper.writeArray(buffer, packet.getContainerMixes(), this::writeContainerMixDataEntry);
        helper.writeArray(buffer, packet.getMaterialReducers(), this::writeMaterialReducerDataEntry);
        buffer.writeBoolean(packet.isClearRecipes());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataPacket packet) {
        helper.readArray(buffer, packet.getShapedRecipes(), this::readShapedRecipePayload);
        helper.readArray(buffer, packet.getShapelessRecipes(), this::readShapelessRecipePayload);
        helper.readArray(buffer, packet.getMultiRecipes(), this::readMultiRecipePayload);
        helper.readArray(buffer, packet.getUserDataShapelessRecipes(), this::readShapelessRecipePayload);
        helper.readArray(buffer, packet.getShapelessChemistryRecipes(), this::readShapelessRecipePayload);
        helper.readArray(buffer, packet.getShapedChemistryRecipes(), this::readShapedRecipePayload);
        helper.readArray(buffer, packet.getSmithingTransformRecipes(), this::readSmithingTransformRecipePayload);
        helper.readArray(buffer, packet.getSmithingTrimRecipes(), this::readSmithingTrimRecipePayload);
        helper.readArray(buffer, packet.getPotionMixes(), this::readPotionMixDataEntry);
        helper.readArray(buffer, packet.getContainerMixes(), this::readContainerMixDataEntry);
        helper.readArray(buffer, packet.getMaterialReducers(), this::readMaterialReducerDataEntry);
        packet.setClearRecipes(buffer.readBoolean());
    }

    protected void writeShapedRecipePayload(ByteBuf buffer, BedrockCodecHelper helper, ShapedRecipePayload payload) {
        helper.writeString(buffer, payload.getRecipeId());
        VarInts.writeInt(buffer, payload.getWidth());
        VarInts.writeInt(buffer, payload.getHeight());
        helper.writeArray(buffer, payload.getIngredients(), this::writeRecipeIngredient);
        helper.writeArray(buffer, payload.getResults(), helper::writeNetworkItemInstanceDescriptor);
        helper.writeUuid(buffer, payload.getUuid());
        helper.writeString(buffer, payload.getTag());
        VarInts.writeInt(buffer, payload.getPriority());
        buffer.writeBoolean(payload.isAssumeSymmetry());
        helper.writeOptionalNull(buffer, payload.getUnlockingRequirement(), this::writeRecipeUnlockingRequirement);
        this.writeRecipeNetId(buffer, payload.getNetId());
    }

    protected ShapedRecipePayload readShapedRecipePayload(ByteBuf buffer, BedrockCodecHelper helper) {
        final ShapedRecipePayload payload = new ShapedRecipePayload();
        payload.setRecipeId(helper.readString(buffer));
        payload.setWidth(VarInts.readInt(buffer));
        payload.setHeight(VarInts.readInt(buffer));
        helper.readArray(buffer, payload.getIngredients(), this::readRecipeIngredient);
        helper.readArray(buffer, payload.getResults(), helper::readNetworkItemInstanceDescriptor);
        payload.setUuid(helper.readUuid(buffer));
        payload.setTag(helper.readString(buffer));
        payload.setPriority(VarInts.readInt(buffer));
        payload.setAssumeSymmetry(buffer.readBoolean());
        payload.setUnlockingRequirement(helper.readOptional(buffer, null, this::readRecipeUnlockingRequirement));
        payload.setNetId(this.readRecipeNetId(buffer));
        return payload;
    }

    protected void writeShapelessRecipePayload(ByteBuf buffer, BedrockCodecHelper helper, ShapelessRecipePayload payload) {
        helper.writeString(buffer, payload.getRecipeId());
        helper.writeArray(buffer, payload.getIngredients(), this::writeRecipeIngredient);
        helper.writeArray(buffer, payload.getResults(), helper::writeNetworkItemInstanceDescriptor);
        helper.writeUuid(buffer, payload.getUuid());
        helper.writeString(buffer, payload.getTag());
        VarInts.writeInt(buffer, payload.getPriority());
        helper.writeOptionalNull(buffer, payload.getUnlockingRequirement(), this::writeRecipeUnlockingRequirement);
        this.writeRecipeNetId(buffer, payload.getNetId());
    }

    protected ShapelessRecipePayload readShapelessRecipePayload(ByteBuf buffer, BedrockCodecHelper helper) {
        final ShapelessRecipePayload payload = new ShapelessRecipePayload();
        payload.setRecipeId(helper.readString(buffer));
        helper.readArray(buffer, payload.getIngredients(), this::readRecipeIngredient);
        helper.readArray(buffer, payload.getResults(), helper::readNetworkItemInstanceDescriptor);
        payload.setUuid(helper.readUuid(buffer));
        payload.setTag(helper.readString(buffer));
        payload.setPriority(VarInts.readInt(buffer));
        payload.setUnlockingRequirement(helper.readOptional(buffer, null, this::readRecipeUnlockingRequirement));
        payload.setNetId(this.readRecipeNetId(buffer));
        return payload;
    }

    protected void writeMultiRecipePayload(ByteBuf buffer, BedrockCodecHelper helper, MultiRecipePayload payload) {
        helper.writeUuid(buffer, payload.getMultiRecipeUUID());
        this.writeRecipeNetId(buffer, payload.getNetId());
    }

    protected MultiRecipePayload readMultiRecipePayload(ByteBuf buffer, BedrockCodecHelper helper) {
        final MultiRecipePayload payload = new MultiRecipePayload();
        payload.setMultiRecipeUUID(helper.readUuid(buffer));
        payload.setNetId(this.readRecipeNetId(buffer));
        return payload;
    }

    protected void writeSmithingTransformRecipePayload(ByteBuf buffer, BedrockCodecHelper helper, SmithingTransformRecipePayload payload) {
        helper.writeString(buffer, payload.getRecipeId());
        this.writeRecipeIngredient(buffer, helper, payload.getTemplateIngredient());
        this.writeRecipeIngredient(buffer, helper, payload.getBaseIngredient());
        this.writeRecipeIngredient(buffer, helper, payload.getAdditionIngredient());
        helper.writeNetworkItemInstanceDescriptor(buffer, payload.getResult());
        helper.writeString(buffer, payload.getTag());
        this.writeRecipeNetId(buffer, payload.getNetId());
    }

    protected SmithingTransformRecipePayload readSmithingTransformRecipePayload(ByteBuf buffer, BedrockCodecHelper helper) {
        final SmithingTransformRecipePayload payload = new SmithingTransformRecipePayload();
        payload.setRecipeId(helper.readString(buffer));
        payload.setTemplateIngredient(this.readRecipeIngredient(buffer, helper));
        payload.setBaseIngredient(this.readRecipeIngredient(buffer, helper));
        payload.setAdditionIngredient(this.readRecipeIngredient(buffer, helper));
        payload.setResult(helper.readNetworkItemInstanceDescriptor(buffer));
        payload.setTag(helper.readString(buffer));
        payload.setNetId(this.readRecipeNetId(buffer));
        return payload;
    }

    protected void writeSmithingTrimRecipePayload(ByteBuf buffer, BedrockCodecHelper helper, SmithingTrimRecipePayload payload) {
        helper.writeString(buffer, payload.getRecipeId());
        this.writeRecipeIngredient(buffer, helper, payload.getTemplateIngredient());
        this.writeRecipeIngredient(buffer, helper, payload.getBaseIngredient());
        this.writeRecipeIngredient(buffer, helper, payload.getAdditionIngredient());
        helper.writeString(buffer, payload.getTag());
        this.writeRecipeNetId(buffer, payload.getNetId());
    }

    protected SmithingTrimRecipePayload readSmithingTrimRecipePayload(ByteBuf buffer, BedrockCodecHelper helper) {
        final SmithingTrimRecipePayload payload = new SmithingTrimRecipePayload();
        payload.setRecipeId(helper.readString(buffer));
        payload.setTemplateIngredient(this.readRecipeIngredient(buffer, helper));
        payload.setBaseIngredient(this.readRecipeIngredient(buffer, helper));
        payload.setAdditionIngredient(this.readRecipeIngredient(buffer, helper));
        payload.setTag(helper.readString(buffer));
        payload.setNetId(this.readRecipeNetId(buffer));
        return payload;
    }

    protected void writePotionMixDataEntry(ByteBuf buffer, BedrockCodecHelper helper, PotionMixDataEntry entry) {
        VarInts.writeInt(buffer, entry.getFromPotionId());
        VarInts.writeInt(buffer, entry.getFromItemAux());
        VarInts.writeInt(buffer, entry.getReagentItemId());
        VarInts.writeInt(buffer, entry.getReagentItemAux());
        VarInts.writeInt(buffer, entry.getToPotionId());
        VarInts.writeInt(buffer, entry.getToItemAux());
    }

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

    protected void writeContainerMixDataEntry(ByteBuf buffer, BedrockCodecHelper helper, ContainerMixDataEntry entry) {
        VarInts.writeInt(buffer, entry.getFromItemId());
        VarInts.writeInt(buffer, entry.getReagentItemId());
        VarInts.writeInt(buffer, entry.getOutputItemId());
    }

    protected ContainerMixDataEntry readContainerMixDataEntry(ByteBuf buffer, BedrockCodecHelper helper) {
        final ContainerMixDataEntry entry = new ContainerMixDataEntry();
        entry.setFromItemId(VarInts.readInt(buffer));
        entry.setReagentItemId(VarInts.readInt(buffer));
        entry.setOutputItemId(VarInts.readInt(buffer));
        return entry;
    }

    protected void writeMaterialReducerDataEntry(ByteBuf buffer, BedrockCodecHelper helper, MaterialReducerDataEntry entry) {
        VarInts.writeInt(buffer, entry.getFromItemKey());
        helper.writeArray(buffer, entry.getItemIdsAndCounts(), this::writeMaterialReducerEntryOutput);
    }

    protected MaterialReducerDataEntry readMaterialReducerDataEntry(ByteBuf buffer, BedrockCodecHelper helper) {
        final MaterialReducerDataEntry entry = new MaterialReducerDataEntry();
        entry.setFromItemKey(VarInts.readInt(buffer));
        helper.readArray(buffer, entry.getItemIdsAndCounts(), this::readMaterialReducerEntryOutput);
        return entry;
    }

    protected void writeMaterialReducerEntryOutput(ByteBuf buffer, BedrockCodecHelper helper, MaterialReducerEntryOutput output) {
        VarInts.writeInt(buffer, output.getItemId());
        VarInts.writeInt(buffer, output.getItemCount());
    }

    protected MaterialReducerEntryOutput readMaterialReducerEntryOutput(ByteBuf buffer, BedrockCodecHelper helper) {
        final MaterialReducerEntryOutput output = new MaterialReducerEntryOutput();
        output.setItemId(VarInts.readInt(buffer));
        output.setItemCount(VarInts.readInt(buffer));
        return output;
    }

    protected void writeRecipeUnlockingRequirement(ByteBuf buffer, BedrockCodecHelper helper, RecipeUnlockingRequirement unlockingRequirement) {
        VarInts.writeInt(buffer, unlockingRequirement.getUnlockingContext().ordinal());
        final boolean hasValue = !unlockingRequirement.getUnlockingIngredients().isEmpty();
        buffer.writeBoolean(hasValue);
        if (hasValue) {
            helper.writeArray(buffer, unlockingRequirement.getUnlockingIngredients(), this::writeRecipeIngredient);
        }
    }

    protected RecipeUnlockingRequirement readRecipeUnlockingRequirement(ByteBuf buffer, BedrockCodecHelper helper) {
        final RecipeUnlockingContext unlockingContext = RecipeUnlockingContext.from(VarInts.readInt(buffer));
        final RecipeUnlockingRequirement unlockingRequirement = new RecipeUnlockingRequirement(unlockingContext);
        if (buffer.readBoolean()) {
            helper.readArray(buffer, unlockingRequirement.getUnlockingIngredients(), this::readRecipeIngredient);
        }
        return unlockingRequirement;
    }

    protected void writeRecipeIngredient(ByteBuf buffer, BedrockCodecHelper helper, RecipeIngredient ingredient) {
        final boolean isInvalid = ingredient.getDescriptor().getType().equals(ItemDescriptorType.EMPTY);
        VarInts.writeUnsignedInt(buffer, isInvalid ? 0 : 1);
        if (isInvalid) {
            VarInts.writeInt(buffer, this.toAuxValue(-1));
            VarInts.writeInt(buffer, 0);
        } else {
            helper.writeString(buffer, ingredient.getDescriptor().getType().getId());
            this.writeItemDescriptor(buffer, helper, ingredient.getDescriptor());
            VarInts.writeInt(buffer, ingredient.getStackSize());
        }
    }

    protected RecipeIngredient readRecipeIngredient(ByteBuf buffer, BedrockCodecHelper helper) {
        final int variant = VarInts.readUnsignedInt(buffer);
        if (variant == 0) {
            VarInts.readInt(buffer); // auxValue
            final int stackSize = VarInts.readInt(buffer);
            return new RecipeIngredient(EmptyDescriptor.INSTANCE, stackSize);
        } else {
            final ItemDescriptorType type = ItemDescriptorType.from(helper.readString(buffer));
            final ItemDescriptor descriptor = this.readItemDescriptor(buffer, helper, type);
            final int stackSize = VarInts.readInt(buffer);
            return new RecipeIngredient(descriptor, stackSize);
        }
    }

    protected void writeItemDescriptor(ByteBuf buffer, BedrockCodecHelper helper, ItemDescriptor descriptor) {
        switch (descriptor.getType()) {
            case NAME:
                helper.writeString(buffer, ((NameDescriptor) descriptor).getItemId().getIdentifier());
                VarInts.writeInt(buffer, this.toAuxValue(((NameDescriptor) descriptor).getAuxValue()));
                break;
            case MOLANG:
                helper.writeString(buffer, ((MolangDescriptor) descriptor).getTagExpression());
                buffer.writeShortLE(((MolangDescriptor) descriptor).getMolangVersion());
                break;
            case ITEM_TAG:
                helper.writeString(buffer, ((ItemTagDescriptor) descriptor).getItemTag());
                VarInts.writeInt(buffer, this.toAuxValue(-1));
                break;
        }
    }

    protected ItemDescriptor readItemDescriptor(ByteBuf buffer, BedrockCodecHelper helper, ItemDescriptorType type) {
        ItemDescriptor descriptor;
        switch (type) {
            case NAME:
                final ItemDefinition definition = helper.getItemDefinitions().getDefinition(
                        helper.getItemDefinitions().getRuntimeIdByName(helper.readString(buffer))
                );
                final int auxValue = this.fromAuxValue(VarInts.readInt(buffer));
                descriptor = new NameDescriptor(definition, auxValue);
                break;
            case MOLANG:
                descriptor = new MolangDescriptor(helper.readString(buffer), buffer.readShortLE());
                break;
            case ITEM_TAG:
                descriptor = new ItemTagDescriptor(helper.readString(buffer));
                this.fromAuxValue(VarInts.readInt(buffer));
                break;
            default:
                descriptor = EmptyDescriptor.INSTANCE;
                break;
        }
        return descriptor;
    }

    protected void writeRecipeNetId(ByteBuf buffer, RecipeNetId netId) {
        VarInts.writeInt(buffer, netId.getRawId());
    }

    protected RecipeNetId readRecipeNetId(ByteBuf buffer) {
        return new RecipeNetId(VarInts.readInt(buffer));
    }

    protected int fromAuxValue(int value) {
        return value == 0x7fff ? -1 : value;
    }

    protected int toAuxValue(int value) {
        return value == -1 ? 0x7fff : value;
    }
}
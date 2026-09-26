package org.cloudburstmc.protocol.bedrock.codec.v554;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v534.BedrockCodecHelper_v534;
import org.cloudburstmc.protocol.bedrock.data.ability.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.*;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequest;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestId;
import org.cloudburstmc.protocol.bedrock.data.recipe.RecipeIngredient;
import org.cloudburstmc.protocol.bedrock.data.text.TextProcessingEventOrigin;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

public class BedrockCodecHelper_v554 extends BedrockCodecHelper_v534 {

    protected static final ItemDescriptorType[] DESCRIPTOR_TYPES = ItemDescriptorType.values();
    protected final TypeMap<TextProcessingEventOrigin> textProcessingEventOrigins;

    public BedrockCodecHelper_v554(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes,
                                   TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerEnumName> containerEnumNames,
                                   TypeMap<AbilitiesIndex> abilities, TypeMap<TextProcessingEventOrigin> textProcessingEventOrigins) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerEnumNames, abilities);
        this.textProcessingEventOrigins = textProcessingEventOrigins;
    }

    @Override
    public void writeItemStackRequest(ByteBuf buffer, ItemStackRequest request) {
        VarInts.writeInt(buffer, request.getClientRequestId().getID());
        this.writeArray(
                buffer,
                request.getActions(),
                (buf, codecHelper, object) -> this.itemStackRequestActionsVariant.write(buf, codecHelper, null, object)
        );
        this.writeArray(buffer, request.getStringsToFilter(), this::writeString);
        final TextProcessingEventOrigin origin = request.getStringsToFilterOrigin(); // new for v554
        buffer.writeIntLE(origin == null ? -1 : this.textProcessingEventOrigins.getId(origin));
    }

    @Override
    public ItemStackRequest readItemStackRequest(ByteBuf buffer) {
        final ItemStackRequest request = new ItemStackRequest();
        request.setClientRequestId(new ItemStackRequestId(VarInts.readInt(buffer)));
        this.readArray(
                buffer,
                request.getActions(),
                (buf, codecHelper) -> this.itemStackRequestActionsVariant.read(buf, codecHelper, null),
                this.getEncodingSettings().maxInventoryActionsOrRequests()
        );
        this.readArray(buffer, request.getStringsToFilter(), (buf, helper) -> helper.readStringMaxLen(buf, 1000));
        final int originVal = buffer.readIntLE(); // new for v554
        request.setStringsToFilterOrigin(originVal == -1 ? null : this.textProcessingEventOrigins.getType(originVal));
        return request;
    }

    @Override
    public RecipeIngredient readIngredient(ByteBuf buffer) {
        ItemDescriptorType type = DESCRIPTOR_TYPES[buffer.readUnsignedByte()];
        ItemDescriptor descriptor = this.readItemDescriptor(buffer, type);
        return new RecipeIngredient(descriptor, VarInts.readInt(buffer));
    }

    protected ItemDescriptor readItemDescriptor(ByteBuf buffer, ItemDescriptorType type) {
        ItemDescriptor descriptor;
        switch (type) {
            case NAME:
                int itemId = buffer.readShortLE();
                ItemDefinition definition = itemId == 0 ? ItemDefinition.AIR : this.getItemDefinitions().getDefinition(itemId);
                int auxValue = itemId != 0 ? buffer.readShortLE() : 0;
                descriptor = new DefaultDescriptor(definition, auxValue);
                break;
            case MOLANG:
                descriptor = new MolangDescriptor(this.readString(buffer), buffer.readUnsignedByte());
                break;
            case ITEM_TAG:
                descriptor = new ItemTagDescriptor(this.readString(buffer));
                break;
            case DEFERRED:
                descriptor = new DeferredDescriptor(this.readString(buffer), buffer.readShortLE());
                break;
            default:
                descriptor = InvalidDescriptor.INSTANCE;
                break;
        }
        return descriptor;
    }

    @Override
    public void writeIngredient(ByteBuf buffer, RecipeIngredient ingredient) {
        buffer.writeByte(ingredient.getDescriptor().getType().ordinal());
        this.writeItemDescriptor(buffer, ingredient.getDescriptor());
        VarInts.writeInt(buffer, ingredient.getStackSize());
    }

    protected void writeItemDescriptor(ByteBuf buffer, ItemDescriptor descriptor) {
        switch (descriptor.getType()) {
            case NAME:
                DefaultDescriptor defaultDescriptor = (DefaultDescriptor) descriptor;
                boolean empty = defaultDescriptor.getItemId() == null || defaultDescriptor.getItemId().getRuntimeId() == 0;
                buffer.writeShortLE(empty ? 0 : defaultDescriptor.getItemId().getRuntimeId());
                if (!empty) {
                    buffer.writeShortLE(defaultDescriptor.getAuxValue());
                }
                break;
            case MOLANG:
                MolangDescriptor molangDescriptor = (MolangDescriptor) descriptor;
                this.writeString(buffer, molangDescriptor.getTagExpression());
                buffer.writeByte(molangDescriptor.getMolangVersion());
                break;
            case ITEM_TAG:
                ItemTagDescriptor tagDescriptor = (ItemTagDescriptor) descriptor;
                this.writeString(buffer, tagDescriptor.getItemTag());
                break;
            case DEFERRED:
                DeferredDescriptor deferredDescriptor = (DeferredDescriptor) descriptor;
                this.writeString(buffer, deferredDescriptor.getFullName());
                buffer.writeShortLE(deferredDescriptor.getAuxValue());
                break;
        }
    }
}

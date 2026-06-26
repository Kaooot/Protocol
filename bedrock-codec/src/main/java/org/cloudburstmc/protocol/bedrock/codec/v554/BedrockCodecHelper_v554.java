package org.cloudburstmc.protocol.bedrock.codec.v554;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v534.BedrockCodecHelper_v534;
import org.cloudburstmc.protocol.bedrock.data.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.*;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequest;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.TextProcessingEventOrigin;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.List;

public class BedrockCodecHelper_v554 extends BedrockCodecHelper_v534 {

    protected static final ItemDescriptorType[] DESCRIPTOR_TYPES = ItemDescriptorType.values();
    protected final TypeMap<TextProcessingEventOrigin> textProcessingEventOrigins;

    public BedrockCodecHelper_v554(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes,
                                   TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerEnumName> containerSlotTypes,
                                   TypeMap<AbilitiesIndex> abilities, TypeMap<TextProcessingEventOrigin> textProcessingEventOrigins) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerSlotTypes, abilities);
        this.textProcessingEventOrigins = textProcessingEventOrigins;
    }

    @Override
    public ItemStackRequest readItemStackRequest(ByteBuf buffer) {
        int requestId = VarInts.readInt(buffer);
        List<ItemStackRequestAction> actions = new ObjectArrayList<>();

        this.readArray(buffer, actions, byteBuf -> {
            ItemStackRequestActionType type = this.stackRequestActionTypes.getType(byteBuf.readByte());
            return readRequestActionData(byteBuf, type);
        }, 32);
        List<String> filteredStrings = new ObjectArrayList<>();
        this.readArray(buffer, filteredStrings, this::readString);

        int originVal = buffer.readIntLE();
        TextProcessingEventOrigin origin = originVal == -1 ? null : this.textProcessingEventOrigins.getType(originVal);  // new for v552
        return new ItemStackRequest(requestId, actions.toArray(new ItemStackRequestAction[0]), filteredStrings.toArray(new String[0]), origin);
    }

    @Override
    public void writeItemStackRequest(ByteBuf buffer, ItemStackRequest request) {
        super.writeItemStackRequest(buffer, request);
        TextProcessingEventOrigin origin = request.getStringsToFilterOrigin();
        buffer.writeIntLE(origin == null ? -1 : this.textProcessingEventOrigins.getId(origin));  // new for v552
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
                descriptor = new NameDescriptor(definition, auxValue);
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
                descriptor = EmptyDescriptor.INSTANCE;
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
                NameDescriptor nameDescriptor = (NameDescriptor) descriptor;
                boolean empty = nameDescriptor.getItemId() == null || nameDescriptor.getItemId().getRuntimeId() == 0;
                buffer.writeShortLE(empty ? 0 : nameDescriptor.getItemId().getRuntimeId());
                if (!empty) {
                    buffer.writeShortLE(nameDescriptor.getAuxValue());
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

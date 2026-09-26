package org.cloudburstmc.protocol.bedrock.codec.v2192;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v2168.BedrockCodecHelper_v2168;
import org.cloudburstmc.protocol.bedrock.data.ability.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.text.TextProcessingEventOrigin;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.misc.RedactableString;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.ItemStackNetId;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseSlotInfo;
import org.cloudburstmc.protocol.bedrock.data.inventory.InventorySource;
import org.cloudburstmc.protocol.bedrock.data.inventory.InventorySourceFlags;
import org.cloudburstmc.protocol.bedrock.data.inventory.InventorySourceType;
import org.cloudburstmc.protocol.bedrock.data.inventory.transaction.ItemUseActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.transaction.ItemUseClientCooldownState;
import org.cloudburstmc.protocol.bedrock.data.inventory.transaction.ItemUseInventoryTransaction;
import org.cloudburstmc.protocol.bedrock.data.inventory.transaction.ItemUsePredictedResult;
import org.cloudburstmc.protocol.bedrock.data.inventory.transaction.ItemUseTriggerType;
import org.cloudburstmc.protocol.bedrock.data.player.HandSlot;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

import static java.util.Objects.requireNonNull;

public class BedrockCodecHelper_v2192 extends BedrockCodecHelper_v2168 {

    public BedrockCodecHelper_v2192(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes,
                                    TypeMap<ContainerEnumName> containerEnumNames, TypeMap<AbilitiesIndex> abilities, TypeMap<TextProcessingEventOrigin> textProcessingEventOrigins) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerEnumNames, abilities, textProcessingEventOrigins);
    }

    @Override
    protected ItemStackResponseSlotInfo readItemStackResponseSlotInfo(ByteBuf buffer) {
        int requestedSlot = buffer.readUnsignedByte();
        int slot = buffer.readUnsignedByte();
        int amount = buffer.readUnsignedByte();
        int stackNetworkId = buffer.readBoolean() ? VarInts.readInt(buffer) : 0;
        String customName = this.readString(buffer);
        String filteredCustomName = this.readOptional(buffer, null, this::readString);
        int durabilityCorrection = VarInts.readInt(buffer);
        return new ItemStackResponseSlotInfo(requestedSlot, slot, amount, new ItemStackNetId(stackNetworkId),
                new RedactableString(customName, filteredCustomName), durabilityCorrection);

    }

    @Override
    protected void writeItemStackResponseSlotInfo(ByteBuf buffer, ItemStackResponseSlotInfo info) {
        buffer.writeByte(info.getRequestedSlot());
        buffer.writeByte(info.getSlot());
        buffer.writeByte(info.getAmount());
        this.writeOptional(buffer, id->id > 0, info.getItemStackNetId().getID(), VarInts::writeInt);
        this.writeString(buffer, info.getCustomName().getUnredacted());
        this.writeOptionalNull(buffer, info.getCustomName().getRedacted(), this::writeString);
        VarInts.writeInt(buffer, info.getDurabilityCorrection());
    }

    @Override
    public void writeInventorySource(ByteBuf buffer, InventorySource source) {
        VarInts.writeUnsignedInt(buffer, source.getSourceType().ordinal());
        this.writeOptionalNull(buffer, source.getContainerID(), ByteBuf::writeByte);
        this.writeOptionalNull(buffer, source.getBitFlags(),
                (buf, bitFlags) -> VarInts.writeUnsignedInt(buf, bitFlags.ordinal()));
    }

    @Override
    public InventorySource readInventorySource(ByteBuf buffer) {
        final InventorySource source = new InventorySource();
        source.setSourceType(InventorySourceType.from(VarInts.readUnsignedInt(buffer)));
        source.setContainerID(this.readOptional(buffer, null, (buf, helper) -> (int) buf.readByte()));
        source.setBitFlags(this.readOptional(buffer, null, (buf, helper) -> InventorySourceFlags.from(VarInts.readUnsignedInt(buf))));
        return source;
    }

    @Override
    public void writeItemUseInventoryTransaction(ByteBuf buffer, ItemUseInventoryTransaction transaction) {
        VarInts.writeInt(buffer, transaction.getActionType().ordinal());
        buffer.writeByte(transaction.getTriggerType().ordinal());
        this.writeVector3i(buffer, transaction.getPosition());
        buffer.writeByte(transaction.getFace());
        VarInts.writeInt(buffer, transaction.getSlot());
        buffer.writeByte(transaction.getHand().ordinal());
        this.writeNetworkItemStackDescriptor(buffer, transaction.getItem());
        this.writeVector3f(buffer, transaction.getFromPosition());
        this.writeVector3f(buffer, transaction.getClickPosition());
        VarInts.writeUnsignedInt(buffer, transaction.getTargetBlockId().getRuntimeId());
        buffer.writeByte(transaction.getClientInteractPrediction().ordinal());
        buffer.writeByte(transaction.getClientCooldownState().ordinal());
    }

    @Override
    public ItemUseInventoryTransaction readItemUseInventoryTransaction(ByteBuf buffer) {
        final ItemUseInventoryTransaction transaction = new ItemUseInventoryTransaction();
        transaction.setActionType(ItemUseActionType.from(VarInts.readInt(buffer)));
        transaction.setTriggerType(ItemUseTriggerType.from(buffer.readUnsignedByte()));
        transaction.setPosition(this.readVector3i(buffer));
        transaction.setFace(buffer.readByte());
        transaction.setSlot(VarInts.readInt(buffer));
        transaction.setHand(HandSlot.from(buffer.readUnsignedByte()));
        transaction.setItem(this.readNetworkItemStackDescriptor(buffer));
        transaction.setFromPosition(this.readVector3f(buffer));
        transaction.setClickPosition(this.readVector3f(buffer));
        transaction.setTargetBlockId(this.getBlockDefinitions().getDefinition(VarInts.readUnsignedInt(buffer)));
        transaction.setClientInteractPrediction(ItemUsePredictedResult.from(buffer.readUnsignedByte()));
        transaction.setClientCooldownState(ItemUseClientCooldownState.from(buffer.readUnsignedByte()));
        return transaction;
    }
}

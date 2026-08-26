package org.cloudburstmc.protocol.bedrock.codec.v2192;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.ActorDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v2168.BedrockCodecHelper_v2168;
import org.cloudburstmc.protocol.bedrock.data.AbilitiesIndex;
import org.cloudburstmc.protocol.bedrock.data.HandSlot;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.TextProcessingEventOrigin;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseSlotInfo;
import org.cloudburstmc.protocol.bedrock.data.payload.common.RedactableString;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.net.ItemStackNetId;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.*;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.data.ItemUseInventoryTransaction;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
public class BedrockCodecHelper_v2192 extends BedrockCodecHelper_v2168 {

    public BedrockCodecHelper_v2192(ActorDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerEnumName> containerSlotTypes, TypeMap<AbilitiesIndex> abilities, TypeMap<TextProcessingEventOrigin> textProcessingEventOrigins) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerSlotTypes, abilities, textProcessingEventOrigins);
    }

    @Override
    protected void writeItemEntry(ByteBuf buffer, ItemStackResponseSlotInfo itemEntry) {
        buffer.writeByte(itemEntry.getRequestedSlot());
        buffer.writeByte(itemEntry.getSlot());
        buffer.writeByte(itemEntry.getAmount());
        this.writeOptionalNull(
                buffer,
                itemEntry.getItemStackNetId(),
                (buf, helper, itemStackNetId) -> VarInts.writeInt(buf, itemStackNetId.getID())
        );
        this.writeRedactableString(buffer, itemEntry.getCustomName());
        VarInts.writeInt(buffer, itemEntry.getDurabilityCorrection());
    }

    @Override
    protected ItemStackResponseSlotInfo readItemEntry(ByteBuf buffer) {
        int slot = buffer.readUnsignedByte();
        int requestedSlot = buffer.readUnsignedByte();
        int amount = buffer.readUnsignedByte();
        ItemStackNetId stackNetworkId = this.readOptional(
                buffer,
                null,
                (buf, helper) -> new ItemStackNetId(VarInts.readInt(buf))
        );
        final RedactableString customName = this.readRedactableString(buffer);
        int durabilityCorrection = VarInts.readInt(buffer);
        return new ItemStackResponseSlotInfo(
                slot,
                requestedSlot,
                amount,
                stackNetworkId,
                customName,
                durabilityCorrection
        );
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

    @Override
    protected void writeInventorySource(ByteBuf buffer, InventorySource source) {
        VarInts.writeUnsignedInt(buffer, source.getSourceType().ordinal());
        this.writeOptionalNull(buffer, source.getContainerID(), ByteBuf::writeByte);
        this.writeOptionalNull(buffer, source.getBitFlags(),
                (buf, bitFlags) -> VarInts.writeUnsignedInt(buf, bitFlags.ordinal()));
    }

    @Override
    protected InventorySource readInventorySource(ByteBuf buffer) {
        final InventorySource source = new InventorySource();
        source.setSourceType(InventorySourceType.from(VarInts.readUnsignedInt(buffer)));
        source.setContainerID(this.readOptional(buffer, null, (buf, helper) -> (int) buf.readByte()));
        source.setBitFlags(this.readOptional(buffer, null, (buf, helper) -> InventorySourceFlags.from(VarInts.readUnsignedInt(buf))));
        return source;
    }
}
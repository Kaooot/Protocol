package org.cloudburstmc.protocol.bedrock.codec.v428.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v419.serializer.PlayerAuthInputSerializer_v419;
import org.cloudburstmc.protocol.bedrock.data.PlayerActionType;
import org.cloudburstmc.protocol.bedrock.data.PlayerAuthInputData;
import org.cloudburstmc.protocol.bedrock.data.PlayerBlockActionData;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerEnumName;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.net.ItemStackLegacyRequestId;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.ItemUseActionType;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.LegacySetSlot;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.data.ItemUseInventoryTransaction;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.data.PackedLegacyItemUseInventoryTransaction;
import org.cloudburstmc.protocol.bedrock.packet.PlayerAuthInputPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerAuthInputSerializer_v428 extends PlayerAuthInputSerializer_v419 {

    public static final PlayerAuthInputSerializer_v428 INSTANCE = new PlayerAuthInputSerializer_v428();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerAuthInputPacket packet) {
        super.serialize(buffer, helper, packet);

        if (packet.getInputData().contains(PlayerAuthInputData.PERFORM_ITEM_INTERACTION)) {
            this.writePackedLegacyItemUseTransaction(buffer, helper, packet.getItemUseTransaction());
        }

        if (packet.getInputData().contains(PlayerAuthInputData.PERFORM_ITEM_STACK_REQUEST)) {
            helper.writeItemStackRequest(buffer, packet.getItemStackRequest());
        }

        if (packet.getInputData().contains(PlayerAuthInputData.PERFORM_BLOCK_ACTIONS)) {
            VarInts.writeInt(buffer, packet.getPlayerActions().size());
            for (PlayerBlockActionData actionData : packet.getPlayerActions()) {
                writePlayerBlockActionData(buffer, helper, actionData);
            }
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerAuthInputPacket packet) {
        super.deserialize(buffer, helper, packet);

        if (packet.getInputData().contains(PlayerAuthInputData.PERFORM_ITEM_INTERACTION)) {
            packet.setItemUseTransaction(this.readPackedLegacyItemUseTransaction(buffer, helper));
        }

        if (packet.getInputData().contains(PlayerAuthInputData.PERFORM_ITEM_STACK_REQUEST)) {
            packet.setItemStackRequest(helper.readItemStackRequest(buffer));
        }

        if (packet.getInputData().contains(PlayerAuthInputData.PERFORM_BLOCK_ACTIONS)) {
            helper.readArray(buffer, packet.getPlayerActions(), VarInts::readInt, this::readPlayerBlockActionData, 32); // 32 is more than enough
        }
    }

    protected void writePlayerBlockActionData(ByteBuf buffer, BedrockCodecHelper helper, PlayerBlockActionData actionData) {
        VarInts.writeInt(buffer, actionData.getAction().ordinal());
        switch (actionData.getAction()) {
            case START_DESTROY_BLOCK:
            case ABORT_DESTROY_BLOCK:
            case CRACK_BLOCK:
            case PREDICT_DESTROY_BLOCK:
            case CONTINUE_DESTROY_BLOCK:
                helper.writeVector3i(buffer, actionData.getBlockPosition());
                VarInts.writeInt(buffer, actionData.getFace());
        }
    }

    protected PlayerBlockActionData readPlayerBlockActionData(ByteBuf buffer, BedrockCodecHelper helper) {
        PlayerBlockActionData actionData = new PlayerBlockActionData();
        actionData.setAction(PlayerActionType.values()[VarInts.readInt(buffer)]);
        switch (actionData.getAction()) {
            case START_DESTROY_BLOCK:
            case ABORT_DESTROY_BLOCK:
            case CRACK_BLOCK:
            case PREDICT_DESTROY_BLOCK:
            case CONTINUE_DESTROY_BLOCK:
                actionData.setBlockPosition(helper.readVector3i(buffer));
                actionData.setFace(VarInts.readInt(buffer));
        }
        return actionData;
    }

    protected void writePackedLegacyItemUseTransaction(ByteBuf buffer, BedrockCodecHelper helper, PackedLegacyItemUseInventoryTransaction transaction) {
        this.writeLegacyRequestId(buffer, helper, transaction.getLegacyRequestID());

        if (transaction.getLegacyRequestID().getID() < -1 && (transaction.getLegacyRequestID().getID() & 1) == 0) {
            helper.writeArray(buffer, transaction.getLegacySetItemSlots(), this::writeLegacySetSlot);
        }
        this.writeItemUseInventoryTransaction(buffer, helper, transaction.getTransaction());
    }

    protected PackedLegacyItemUseInventoryTransaction readPackedLegacyItemUseTransaction(ByteBuf buffer, BedrockCodecHelper helper) {
        PackedLegacyItemUseInventoryTransaction itemTransaction = new PackedLegacyItemUseInventoryTransaction();
        itemTransaction.setLegacyRequestID(this.readLegacyRequestId(buffer, helper));

        if (itemTransaction.getLegacyRequestID().getID() < -1 && (itemTransaction.getLegacyRequestID().getID() & 1) == 0) {
            helper.readArray(buffer, itemTransaction.getLegacySetItemSlots(), this::readLegacySetSlot);
        }

        itemTransaction.setTransaction(this.readItemUseInventoryTransaction(buffer, helper));
        return itemTransaction;
    }

    protected void writeLegacyRequestId(ByteBuf buffer, BedrockCodecHelper helper, ItemStackLegacyRequestId id) {
        VarInts.writeInt(buffer, id.getID());
    }

    protected ItemStackLegacyRequestId readLegacyRequestId(ByteBuf buffer, BedrockCodecHelper helper) {
        return new ItemStackLegacyRequestId(VarInts.readInt(buffer));
    }

    protected void writeLegacySetSlot(ByteBuf buffer, BedrockCodecHelper helper, LegacySetSlot slot) {
        buffer.writeByte(slot.getContainerEnum().ordinal());
        helper.writeByteArray(buffer, slot.getSlots());
    }

    protected LegacySetSlot readLegacySetSlot(ByteBuf buffer, BedrockCodecHelper helper) {
        final LegacySetSlot slot = new LegacySetSlot();
        slot.setContainerEnum(ContainerEnumName.values()[buffer.readByte()]);
        slot.setSlots(helper.readByteArray(buffer, 89));
        return slot;
    }

    protected void writeItemUseInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper, ItemUseInventoryTransaction transaction) {
        helper.writeInventoryTransactions(buffer, transaction.getActions());
        VarInts.writeUnsignedInt(buffer, transaction.getActionType().ordinal());
        helper.writeVector3i(buffer, transaction.getPosition());
       VarInts.writeInt(buffer, transaction.getFace());
        VarInts.writeUnsignedInt(buffer, transaction.getSlot());
        helper.writeItem(buffer, transaction.getItem());
        helper.writeVector3f(buffer, transaction.getFromPosition());
        helper.writeVector3f(buffer, transaction.getClickPosition());
        VarInts.writeUnsignedInt(buffer, transaction.getTargetBlockId().getRuntimeId());
    }

    protected ItemUseInventoryTransaction readItemUseInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper) {
        final ItemUseInventoryTransaction transaction = new ItemUseInventoryTransaction();
        helper.readInventoryTransactions(buffer, transaction.getActions());
        transaction.setActionType(ItemUseActionType.from(VarInts.readUnsignedInt(buffer)));
        transaction.setPosition(helper.readVector3i(buffer));
        transaction.setFace(VarInts.readInt(buffer));
        transaction.setSlot(VarInts.readUnsignedInt(buffer));
        transaction.setItem(helper.readItem(buffer));
        transaction.setFromPosition(helper.readVector3f(buffer));
        transaction.setClickPosition(helper.readVector3f(buffer));
        transaction.setTargetBlockId(helper.getBlockDefinitions().getDefinition(VarInts.readUnsignedInt(buffer)));
        return transaction;
    }
}
package org.cloudburstmc.protocol.bedrock.codec.v2168.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v1001.serializer.PlayerAuthInputSerializer_v1001;
import org.cloudburstmc.protocol.bedrock.data.*;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.net.ItemStackLegacyRequestId;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.LegacySetSlot;
import org.cloudburstmc.protocol.bedrock.data.payload.inventory.transaction.data.PackedLegacyItemUseInventoryTransaction;
import org.cloudburstmc.protocol.bedrock.packet.PlayerAuthInputPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerAuthInputSerializer_v2168 extends PlayerAuthInputSerializer_v1001 {
    public static final PlayerAuthInputSerializer_v2168 INSTANCE = new PlayerAuthInputSerializer_v2168();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerAuthInputPacket packet) {
        buffer.writeFloatLE(packet.getPlayerRotation().getX());
        buffer.writeFloatLE(packet.getPlayerRotation().getY());
        helper.writeVector3f(buffer, packet.getPosition());
        helper.writeVector2f(buffer, packet.getMoveVector());
        buffer.writeFloatLE(packet.getPlayerRotation().getZ());
        buffer.writeBoolean(true);
        helper.writeArray(buffer, packet.getInputData(),
                (buf, codecHelper, inputData) -> VarInts.writeInt(buf, inputData.ordinal()));
        VarInts.writeUnsignedInt(buffer, packet.getInputMode().ordinal());
        VarInts.writeUnsignedInt(buffer, packet.getPlayMode().ordinal());
        VarInts.writeInt(buffer, packet.getNewInteractionModel().ordinal());
        helper.writeVector2f(buffer, packet.getInteractRotation());
        VarInts.writeUnsignedLong(buffer, packet.getClientTick());
        helper.writeVector3f(buffer, packet.getPosDelta());
        buffer.writeBoolean(true);
        helper.writeOptionalNull(buffer, packet.getItemUseTransaction(), this::writePackedLegacyItemUseInventoryTransaction);
        buffer.writeBoolean(true);
        helper.writeOptionalNull(buffer, packet.getItemStackRequest(), helper::writeItemStackRequest);
        final boolean hasPlayerBlockActions = !packet.getPlayerBlockActions().isEmpty();
        buffer.writeBoolean(true);
        buffer.writeBoolean(hasPlayerBlockActions);
        if (hasPlayerBlockActions) {
            helper.writeArray(buffer, packet.getPlayerBlockActions(), this::writePlayerBlockActionData);
        }
        buffer.writeBoolean(true);
        helper.writeOptionalNull(buffer, packet.getVehicleRotation(), helper::writeVector2f);
        buffer.writeBoolean(true);
        helper.writeOptionalNull(buffer, packet.getClientPredictedVehicle(), VarInts::writeLong);
        helper.writeVector2f(buffer, packet.getAnalogMoveVector());
        helper.writeVector3f(buffer, packet.getCameraOrientation());
        helper.writeVector2f(buffer, packet.getRawMoveVector());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerAuthInputPacket packet) {
        final Vector2f playerRotation = helper.readVector2f(buffer);
        packet.setPosition(helper.readVector3f(buffer));
        packet.setMoveVector(helper.readVector2f(buffer));
        final float playerHeadRotation = buffer.readFloatLE();
        packet.setPlayerRotation(Vector3f.from(playerRotation.getX(), playerRotation.getY(), playerHeadRotation));
        if (buffer.readBoolean()) {
            helper.readArray(buffer, packet.getInputData(),
                    (buf, codecHelper) -> PlayerAuthInputData.from(VarInts.readInt(buf)));
        }
        packet.setInputMode(InputMode.from(VarInts.readUnsignedInt(buffer)));
        packet.setPlayMode(ClientPlayMode.from(VarInts.readUnsignedInt(buffer)));
        packet.setNewInteractionModel(InputInteractionModel.from(VarInts.readInt(buffer)));
        packet.setInteractRotation(helper.readVector2f(buffer));
        packet.setClientTick(VarInts.readUnsignedLong(buffer));
        packet.setPosDelta(helper.readVector3f(buffer));
        if (buffer.readBoolean()) {
            packet.setItemUseTransaction(helper.readOptional(buffer, null, this::readPackedLegacyItemUseInventoryTransaction));
        }
        if (buffer.readBoolean()) {
            packet.setItemStackRequest(helper.readOptional(buffer, null, helper::readItemStackRequest));
        }
        if (buffer.readBoolean() && buffer.readBoolean()) {
            helper.readArray(buffer, packet.getPlayerBlockActions(), this::readPlayerBlockActionData, helper.getEncodingSettings().maxPlayerBlockActionDataSize());
        }
        if (buffer.readBoolean()) {
            packet.setVehicleRotation(helper.readOptional(buffer, null, helper::readVector2f));
        }
        if (buffer.readBoolean()) {
            packet.setClientPredictedVehicle(helper.readOptional(buffer, null, VarInts::readLong));
        }
        packet.setAnalogMoveVector(helper.readVector2f(buffer));
        packet.setCameraOrientation(helper.readVector3f(buffer));
        packet.setRawMoveVector(helper.readVector2f(buffer));
    }

    @Override
    protected void writePlayerBlockActionData(ByteBuf buffer, BedrockCodecHelper helper, PlayerBlockActionData data) {
        VarInts.writeInt(buffer, data.getPlayerActionType().ordinal());
        helper.writeBlockPosition(buffer, data.getBlockPosition());
        VarInts.writeInt(buffer, data.getFacing());
    }

    @Override
    protected PlayerBlockActionData readPlayerBlockActionData(ByteBuf buffer, BedrockCodecHelper helper) {
        final PlayerBlockActionData data = new PlayerBlockActionData();
        data.setPlayerActionType(PlayerActionType.from(VarInts.readInt(buffer)));
        data.setBlockPosition(helper.readBlockPosition(buffer));
        data.setFacing(VarInts.readInt(buffer));
        return data;
    }

    @Override
    protected void writePackedLegacyItemUseInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper, PackedLegacyItemUseInventoryTransaction transaction) {
        VarInts.writeInt(buffer, transaction.getLegacyRequestID().getID());
        final boolean legacySetItemSlotsHasValue = transaction.getLegacyRequestID().getID() < -1 && (transaction.getLegacyRequestID().getID() & 1) == 0;
        buffer.writeBoolean(legacySetItemSlotsHasValue);
        if (legacySetItemSlotsHasValue) {
            helper.writeArray(buffer, transaction.getLegacySetItemSlots(), (buf, codecHelper, slot) -> {
                codecHelper.writeContainerEnumName(buf, slot.getContainerEnum());
                codecHelper.writeByteArray(buf, slot.getSlots());
            });
        }
        buffer.writeBoolean(true);
        buffer.writeBoolean(true);
        helper.writeArray(buffer, transaction.getActions(), this::writeInventoryAction);
        helper.writeItemUseInventoryTransaction(buffer, transaction.getTransaction());
    }

    @Override
    protected PackedLegacyItemUseInventoryTransaction readPackedLegacyItemUseInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper) {
        final PackedLegacyItemUseInventoryTransaction transaction = new PackedLegacyItemUseInventoryTransaction();
        transaction.setLegacyRequestID(new ItemStackLegacyRequestId(VarInts.readInt(buffer)));
        if (buffer.readBoolean()) {
            helper.readArray(buffer, transaction.getLegacySetItemSlots(), (buf, codecHelper) -> {
                final LegacySetSlot slot = new LegacySetSlot();
                slot.setContainerEnum(codecHelper.readContainerEnumName(buf));
                slot.setSlots(codecHelper.readByteArray(buf));
                return slot;
            });
        }
        if (buffer.readBoolean() && buffer.readBoolean()) {
            helper.readArray(buffer, transaction.getActions(), this::readInventoryAction, helper.getEncodingSettings().maxInventoryActionsOrRequests());
        }
        transaction.setTransaction(helper.readItemUseInventoryTransaction(buffer));
        return transaction;
    }
}
package org.cloudburstmc.protocol.bedrock.codec.v2193.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v2168.serializer.PlayerAuthInputSerializer_v2168;
import org.cloudburstmc.protocol.bedrock.data.inventory.LegacySetSlot;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.ItemStackLegacyRequestId;
import org.cloudburstmc.protocol.bedrock.data.inventory.transaction.PackedItemUseLegacyInventoryTransaction;
import org.cloudburstmc.protocol.bedrock.data.player.input.InputMode;
import org.cloudburstmc.protocol.bedrock.data.player.input.NewInteractionModel;
import org.cloudburstmc.protocol.bedrock.data.player.input.PlayerAuthInputData;
import org.cloudburstmc.protocol.bedrock.packet.PlayerAuthInputPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerAuthInputSerializer_v2193 extends PlayerAuthInputSerializer_v2168 {
    public static final PlayerAuthInputSerializer_v2193 INSTANCE = new PlayerAuthInputSerializer_v2193();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerAuthInputPacket packet) {
        helper.writeVector2f(buffer, packet.getPlayerRotation());
        helper.writeVector3f(buffer, packet.getPosition());
        helper.writeVector2f(buffer, packet.getMoveVector());
        buffer.writeFloatLE(packet.getPlayerHeadRotation());
        helper.writeArray(buffer, packet.getInputData(),
                (buf, codecHelper, inputData) -> VarInts.writeInt(buf, inputData.ordinal()));
        VarInts.writeUnsignedInt(buffer, packet.getInputMode().ordinal());
        VarInts.writeUnsignedInt(buffer, packet.getPlayMode().ordinal());
        VarInts.writeInt(buffer, packet.getNewInteractionModel().ordinal());
        helper.writeVector2f(buffer, packet.getInteractRotation());
        helper.writePlayerInputTick(buffer, packet.getClientTick());
        helper.writeVector3f(buffer, packet.getPosDelta());
        helper.writeOptionalNull(buffer, packet.getItemUseTransaction(), this::writePackedItemUseLegacyInventoryTransaction);
        helper.writeOptionalNull(buffer, packet.getItemStackRequest(), helper::writeItemStackRequest);
        helper.writeOptional(
                buffer,
                o -> !packet.getPlayerBlockActions().isEmpty(),
                packet.getPlayerBlockActions(),
                (buf, codecHelper, playerBlockActions) ->
                        codecHelper.writeArray(buf, packet.getPlayerBlockActions(), this::writePlayerBlockActionData)
        );
        helper.writeOptionalNull(buffer, packet.getVehicleRotation(), helper::writeVector2f);
        helper.writeOptionalNull(buffer, packet.getClientPredictedVehicle(), VarInts::writeLong);
        helper.writeVector2f(buffer, packet.getAnalogMoveVector());
        helper.writeVector3f(buffer, packet.getCameraOrientation());
        helper.writeVector2f(buffer, packet.getRawMoveVector());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerAuthInputPacket packet) {
        packet.setPlayerRotation(helper.readVector2f(buffer));
        packet.setPosition(helper.readVector3f(buffer));
        packet.setMoveVector(helper.readVector2f(buffer));
        packet.setPlayerHeadRotation(buffer.readFloatLE());
        helper.readArray(buffer, packet.getInputData(),
                (buf, codecHelper) -> PlayerAuthInputData.from(VarInts.readInt(buf)));
        packet.setInputMode(InputMode.from(VarInts.readUnsignedInt(buffer)));
        packet.setPlayMode(CLIENT_PLAY_MODES.getType(VarInts.readUnsignedInt(buffer)));
        packet.setNewInteractionModel(NewInteractionModel.from(VarInts.readInt(buffer)));
        packet.setInteractRotation(helper.readVector2f(buffer));
        packet.setClientTick(helper.readPlayerInputTick(buffer));
        packet.setPosDelta(helper.readVector3f(buffer));
        packet.setItemUseTransaction(helper.readOptional(buffer, null, this::readPackedItemUseLegacyInventoryTransaction));
        packet.setItemStackRequest(helper.readOptional(buffer, null, helper::readItemStackRequest));
        helper.readOptional(buffer, null, (buf, codecHelper) -> {
            codecHelper.readArray(
                    buf,
                    packet.getPlayerBlockActions(),
                    this::readPlayerBlockActionData,
                    codecHelper.getEncodingSettings().maxPlayerBlockActionDataSize()
            );
            return null;
        });
        packet.setVehicleRotation(helper.readOptional(buffer, null, helper::readVector2f));
        packet.setClientPredictedVehicle(helper.readOptional(buffer, null, VarInts::readLong));
        packet.setAnalogMoveVector(helper.readVector2f(buffer));
        packet.setCameraOrientation(helper.readVector3f(buffer));
        packet.setRawMoveVector(helper.readVector2f(buffer));
    }

    @Override
    protected void writePackedItemUseLegacyInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper, PackedItemUseLegacyInventoryTransaction transaction) {
        VarInts.writeInt(buffer, transaction.getLegacyRequestID().getID());
        helper.writeOptional(
                buffer,
                o -> !transaction.getLegacySetItemSlots().isEmpty(),
                transaction.getLegacySetItemSlots(),
                (buf, codecHelper, legacySetSlots) ->
                        codecHelper.writeArray(buf, transaction.getLegacySetItemSlots(),
                                (buf1, helper1, slot) -> {
                                    helper1.writeContainerEnumName(buf1, slot.getContainerEnum());
                                    helper1.writeByteArray(buf1, slot.getSlots());
                                }
                        )
        );
        helper.writeArray(buffer, transaction.getActions(), helper::writeInventoryAction);
        helper.writeItemUseInventoryTransaction(buffer, transaction.getTransaction());
    }

    @Override
    protected PackedItemUseLegacyInventoryTransaction readPackedItemUseLegacyInventoryTransaction(ByteBuf buffer, BedrockCodecHelper helper) {
        final PackedItemUseLegacyInventoryTransaction transaction = new PackedItemUseLegacyInventoryTransaction();
        transaction.setLegacyRequestID(new ItemStackLegacyRequestId(VarInts.readInt(buffer)));
        helper.readOptional(buffer, null, (buf, codecHelper) -> {
            codecHelper.readArray(buf, transaction.getLegacySetItemSlots(), (buf1, helper1) -> {
                final LegacySetSlot slot = new LegacySetSlot();
                slot.setContainerEnum(helper1.readContainerEnumName(buf1));
                slot.setSlots(helper1.readByteArray(buf1));
                return slot;
            });
            return null;
        });
        helper.readArray(
                buffer,
                transaction.getActions(),
                helper::readInventoryAction,
                helper.getEncodingSettings().maxInventoryActionsOrRequests()
        );
        transaction.setTransaction(helper.readItemUseInventoryTransaction(buffer));
        return transaction;
    }
}
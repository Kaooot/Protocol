package org.cloudburstmc.protocol.bedrock.codec.v766.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v748.serializer.PlayerAuthInputSerializer_v748;
import org.cloudburstmc.protocol.bedrock.data.player.input.InputMode;
import org.cloudburstmc.protocol.bedrock.data.player.input.PlayerAuthInputData;
import org.cloudburstmc.protocol.bedrock.data.player.PlayerBlockActionData;
import org.cloudburstmc.protocol.bedrock.packet.PlayerAuthInputPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerAuthInputSerializer_v766 extends PlayerAuthInputSerializer_v748 {

    public static final PlayerAuthInputSerializer_v766 INSTANCE = new PlayerAuthInputSerializer_v766();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerAuthInputPacket packet) {
        helper.writeVector2f(buffer, packet.getPlayerRotation());
        helper.writeVector3f(buffer, packet.getPosition());
        helper.writeVector2f(buffer, packet.getMoveVector());
        buffer.writeFloatLE(packet.getPlayerHeadRotation());
        helper.writeLargeVarIntFlags(buffer, packet.getInputData(), PlayerAuthInputData.class);
        VarInts.writeUnsignedInt(buffer, packet.getInputMode().ordinal());
        VarInts.writeUnsignedInt(buffer, packet.getPlayMode().ordinal());
        writeInteractionModel(buffer, helper, packet);
        helper.writeVector2f(buffer, packet.getInteractRotation());
        helper.writePlayerInputTick(buffer, packet.getClientTick());
        helper.writeVector3f(buffer, packet.getPosDelta());
        if (packet.getInputData().contains(PlayerAuthInputData.PERFORM_ITEM_INTERACTION)) {
            this.writePackedItemUseLegacyInventoryTransaction(buffer, helper, packet.getItemUseTransaction());
        }
        if (packet.getInputData().contains(PlayerAuthInputData.PERFORM_ITEM_STACK_REQUEST)) {
            helper.writeItemStackRequest(buffer, packet.getItemStackRequest());
        }
        if (packet.getInputData().contains(PlayerAuthInputData.PERFORM_BLOCK_ACTIONS)) {
            VarInts.writeInt(buffer, packet.getPlayerBlockActions().size());
            for (PlayerBlockActionData actionData : packet.getPlayerBlockActions()) {
                writePlayerBlockActionData(buffer, helper, actionData);
            }
        }
        if (packet.getInputData().contains(PlayerAuthInputData.IS_IN_CLIENT_PREDICTED_VEHICLE)) {
            helper.writeVector2f(buffer, packet.getVehicleRotation());
            VarInts.writeLong(buffer, packet.getClientPredictedVehicle());
        }
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
        helper.readLargeVarIntFlags(buffer, packet.getInputData(), PlayerAuthInputData.class);
        packet.setInputMode(InputMode.from(VarInts.readUnsignedInt(buffer)));
        packet.setPlayMode(CLIENT_PLAY_MODES.getType(VarInts.readUnsignedInt(buffer)));
        readInteractionModel(buffer, helper, packet);
        packet.setInteractRotation(helper.readVector2f(buffer));
        packet.setClientTick(helper.readPlayerInputTick(buffer));
        packet.setPosDelta(helper.readVector3f(buffer));
        if (packet.getInputData().contains(PlayerAuthInputData.PERFORM_ITEM_INTERACTION)) {
            packet.setItemUseTransaction(this.readPackedItemUseLegacyInventoryTransaction(buffer, helper));
        }
        if (packet.getInputData().contains(PlayerAuthInputData.PERFORM_ITEM_STACK_REQUEST)) {
            packet.setItemStackRequest(helper.readItemStackRequest(buffer));
        }
        if (packet.getInputData().contains(PlayerAuthInputData.PERFORM_BLOCK_ACTIONS)) {
            helper.readArray(buffer, packet.getPlayerBlockActions(), VarInts::readInt, this::readPlayerBlockActionData, helper.getEncodingSettings().maxPlayerBlockActionDataSize());
        }
        if (packet.getInputData().contains(PlayerAuthInputData.IS_IN_CLIENT_PREDICTED_VEHICLE)) {
            packet.setVehicleRotation(helper.readVector2f(buffer));
            packet.setClientPredictedVehicle(VarInts.readLong(buffer));
        }
        packet.setAnalogMoveVector(helper.readVector2f(buffer));
        packet.setCameraOrientation(helper.readVector3f(buffer));
        packet.setRawMoveVector(helper.readVector2f(buffer));
    }
}
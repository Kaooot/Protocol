package org.cloudburstmc.protocol.bedrock.codec.v2187.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v2168.serializer.PlayerAuthInputSerializer_v2168;
import org.cloudburstmc.protocol.bedrock.data.ClientPlayMode;
import org.cloudburstmc.protocol.bedrock.data.InputInteractionModel;
import org.cloudburstmc.protocol.bedrock.data.InputMode;
import org.cloudburstmc.protocol.bedrock.data.PlayerAuthInputData;
import org.cloudburstmc.protocol.bedrock.packet.PlayerAuthInputPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerAuthInputSerializer_v2187 extends PlayerAuthInputSerializer_v2168 {
    public static final PlayerAuthInputSerializer_v2187 INSTANCE = new PlayerAuthInputSerializer_v2187();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerAuthInputPacket packet) {
        buffer.writeFloatLE(packet.getPlayerRotation().getX());
        buffer.writeFloatLE(packet.getPlayerRotation().getY());
        helper.writeVector3f(buffer, packet.getPosition());
        helper.writeVector2f(buffer, packet.getMoveVector());
        buffer.writeFloatLE(packet.getPlayerRotation().getZ());
        helper.writeArray(buffer, packet.getInputData(),
                (buf, codecHelper, inputData) -> VarInts.writeInt(buf, inputData.ordinal()));
        VarInts.writeUnsignedInt(buffer, packet.getInputMode().ordinal());
        VarInts.writeUnsignedInt(buffer, packet.getPlayMode().ordinal());
        VarInts.writeInt(buffer, packet.getNewInteractionModel().ordinal());
        helper.writeVector2f(buffer, packet.getInteractRotation());
        VarInts.writeUnsignedLong(buffer, packet.getClientTick());
        helper.writeVector3f(buffer, packet.getPosDelta());
        helper.writeOptionalNull(buffer, packet.getItemUseTransaction(), this::writePackedLegacyItemUseInventoryTransaction);
        helper.writeOptionalNull(buffer, packet.getItemStackRequest(), helper::writeItemStackRequest);
        final boolean hasPlayerBlockActions = !packet.getPlayerBlockActions().isEmpty();
        buffer.writeBoolean(hasPlayerBlockActions);
        if (hasPlayerBlockActions) {
            helper.writeArray(buffer, packet.getPlayerBlockActions(), this::writePlayerBlockActionData);
        }
        helper.writeOptionalNull(buffer, packet.getVehicleRotation(), helper::writeVector2f);
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
        helper.readArray(buffer, packet.getInputData(),
                (buf, codecHelper) -> PlayerAuthInputData.from(VarInts.readInt(buf)));
        packet.setInputMode(InputMode.from(VarInts.readUnsignedInt(buffer)));
        packet.setPlayMode(ClientPlayMode.from(VarInts.readUnsignedInt(buffer)));
        packet.setNewInteractionModel(InputInteractionModel.from(VarInts.readInt(buffer)));
        packet.setInteractRotation(helper.readVector2f(buffer));
        packet.setClientTick(VarInts.readUnsignedLong(buffer));
        packet.setPosDelta(helper.readVector3f(buffer));
        packet.setItemUseTransaction(helper.readOptional(buffer, null, this::readPackedLegacyItemUseInventoryTransaction));
        packet.setItemStackRequest(helper.readOptional(buffer, null, helper::readItemStackRequest));
        helper.readArray(buffer, packet.getPlayerBlockActions(), this::readPlayerBlockActionData);
        packet.setVehicleRotation(helper.readOptional(buffer, null, helper::readVector2f));
        packet.setClientPredictedVehicle(helper.readOptional(buffer, null, VarInts::readLong));
        packet.setAnalogMoveVector(helper.readVector2f(buffer));
        packet.setCameraOrientation(helper.readVector3f(buffer));
        packet.setRawMoveVector(helper.readVector2f(buffer));
    }
}
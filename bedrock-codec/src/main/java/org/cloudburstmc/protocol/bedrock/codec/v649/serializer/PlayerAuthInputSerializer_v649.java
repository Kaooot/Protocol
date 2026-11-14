package org.cloudburstmc.protocol.bedrock.codec.v649.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v527.serializer.PlayerAuthInputSerializer_v527;
import org.cloudburstmc.protocol.bedrock.data.player.input.PlayerActionType;
import org.cloudburstmc.protocol.bedrock.data.player.input.PlayerAuthInputData;
import org.cloudburstmc.protocol.bedrock.packet.PlayerAuthInputPacket;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * Use PlayerAuthInputSerializer_v527 here because predictedVehicle was added before analogMoveVector
 */
public class PlayerAuthInputSerializer_v649 extends PlayerAuthInputSerializer_v527 {

    public PlayerAuthInputSerializer_v649(TypeMap<PlayerActionType> playerActionTypes) {
        super(playerActionTypes);
    }

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerAuthInputPacket packet) {
        super.serialize(buffer, helper, packet);
        if (packet.getInputData().contains(PlayerAuthInputData.IS_IN_CLIENT_PREDICTED_VEHICLE)) {
            VarInts.writeLong(buffer, packet.getClientPredictedVehicle());
        }
        helper.writeVector2f(buffer, packet.getAnalogMoveVector());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerAuthInputPacket packet) {
        super.deserialize(buffer, helper, packet);
        if (packet.getInputData().contains(PlayerAuthInputData.IS_IN_CLIENT_PREDICTED_VEHICLE)) {
            packet.setClientPredictedVehicle(VarInts.readLong(buffer));
        }
        packet.setAnalogMoveVector(helper.readVector2f(buffer));
    }
}
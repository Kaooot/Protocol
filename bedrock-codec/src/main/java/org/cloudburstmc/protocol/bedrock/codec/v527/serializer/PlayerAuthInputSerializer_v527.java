package org.cloudburstmc.protocol.bedrock.codec.v527.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v428.serializer.PlayerAuthInputSerializer_v428;
import org.cloudburstmc.protocol.bedrock.data.player.input.NewInteractionModel;
import org.cloudburstmc.protocol.bedrock.data.player.input.PlayerActionType;
import org.cloudburstmc.protocol.bedrock.packet.PlayerAuthInputPacket;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

public class PlayerAuthInputSerializer_v527 extends PlayerAuthInputSerializer_v428 {

    public PlayerAuthInputSerializer_v527(TypeMap<PlayerActionType> playerActionTypes) {
        super(playerActionTypes);
    }

    @Override
    protected void readInteractionModel(ByteBuf buffer, BedrockCodecHelper helper, PlayerAuthInputPacket packet) {
        packet.setNewInteractionModel(NewInteractionModel.from(VarInts.readUnsignedInt(buffer)));
    }

    @Override
    protected void writeInteractionModel(ByteBuf buffer, BedrockCodecHelper helper, PlayerAuthInputPacket packet) {
        VarInts.writeUnsignedInt(buffer, packet.getNewInteractionModel().ordinal());
    }
}
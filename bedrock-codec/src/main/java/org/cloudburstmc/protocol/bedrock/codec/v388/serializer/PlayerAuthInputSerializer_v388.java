package org.cloudburstmc.protocol.bedrock.codec.v388.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.player.input.ClientPlayMode;
import org.cloudburstmc.protocol.bedrock.data.player.input.InputMode;
import org.cloudburstmc.protocol.bedrock.data.player.input.PlayerAuthInputData;
import org.cloudburstmc.protocol.bedrock.packet.PlayerAuthInputPacket;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.Set;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerAuthInputSerializer_v388 implements BedrockPacketSerializer<PlayerAuthInputPacket> {

    public static final PlayerAuthInputSerializer_v388 INSTANCE = new PlayerAuthInputSerializer_v388();

    protected static final TypeMap<ClientPlayMode> CLIENT_PLAY_MODES = TypeMap.builder(ClientPlayMode.class)
            .insert(0, ClientPlayMode.NORMAL)
            .insert(1, ClientPlayMode.TEASER)
            .insert(2, ClientPlayMode.SCREEN)
            .insert(3, ClientPlayMode.VIEWER)
            .insert(4, ClientPlayMode.REALITY)
            .insert(5, ClientPlayMode.PLACEMENT)
            .insert(6, ClientPlayMode.LIVING_ROOM)
            .insert(7, ClientPlayMode.EXIT_LEVEL)
            .insert(8, ClientPlayMode.EXIT_LEVEL_LIVING_ROOM)
            .insert(9, ClientPlayMode.NUM_MODES)
            .build();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerAuthInputPacket packet) {
        Vector2f rotation = packet.getPlayerRotation();
        buffer.writeFloatLE(rotation.getX());
        buffer.writeFloatLE(rotation.getY());
        helper.writeVector3f(buffer, packet.getPosition());
        helper.writeVector2f(buffer, packet.getMoveVector());
        buffer.writeFloatLE(packet.getPlayerHeadRotation());
        long flagValue = 0;
        for (PlayerAuthInputData data : packet.getInputData()) {
            flagValue |= (1L << data.ordinal());
        }
        VarInts.writeUnsignedLong(buffer, flagValue);
        VarInts.writeUnsignedInt(buffer, packet.getInputMode().ordinal());
        VarInts.writeUnsignedInt(buffer, CLIENT_PLAY_MODES.getId(packet.getPlayMode()));
        writeInteractionModel(buffer, helper, packet);

        if (packet.getPlayMode().equals(ClientPlayMode.REALITY)) {
            helper.writeVector3f(buffer, packet.getVrGazeDirection());
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerAuthInputPacket packet) {
        packet.setPlayerRotation(Vector2f.from(buffer.readFloatLE(), buffer.readFloatLE()));
        packet.setPosition(helper.readVector3f(buffer));
        packet.setMoveVector(Vector2f.from(buffer.readFloatLE(), buffer.readFloatLE()));
        packet.setPlayerHeadRotation(buffer.readFloatLE());
        long flagValue = VarInts.readUnsignedLong(buffer);
        Set<PlayerAuthInputData> flags = packet.getInputData();
        for (PlayerAuthInputData flag : PlayerAuthInputData.values()) {
            if ((flagValue & (1L << flag.ordinal())) != 0) {
                flags.add(flag);
            }
        }
        packet.setInputMode(InputMode.from(VarInts.readUnsignedInt(buffer)));
        packet.setPlayMode(CLIENT_PLAY_MODES.getType(VarInts.readUnsignedInt(buffer)));
        readInteractionModel(buffer, helper, packet);

        if (packet.getPlayMode().equals(ClientPlayMode.REALITY)) {
            packet.setVrGazeDirection(helper.readVector3f(buffer));
        }
    }

    protected void readInteractionModel(ByteBuf buffer, BedrockCodecHelper helper, PlayerAuthInputPacket packet) {
    }

    protected void writeInteractionModel(ByteBuf buffer, BedrockCodecHelper helper, PlayerAuthInputPacket packet) {
    }
}
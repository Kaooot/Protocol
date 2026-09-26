package org.cloudburstmc.protocol.bedrock.codec.v671.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v649.serializer.CorrectPlayerMovePredictionSerializer_v649;
import org.cloudburstmc.protocol.bedrock.data.player.input.RewindType;
import org.cloudburstmc.protocol.bedrock.packet.CorrectPlayerMovePredictionPacket;

public class CorrectPlayerMovePredictionSerializer_v671 extends CorrectPlayerMovePredictionSerializer_v649 {
    public static final CorrectPlayerMovePredictionSerializer_v671 INSTANCE = new CorrectPlayerMovePredictionSerializer_v671();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CorrectPlayerMovePredictionPacket packet) {
        buffer.writeByte(packet.getPredictionType().ordinal());
        helper.writeVector3f(buffer, packet.getPos());
        helper.writeVector3f(buffer, packet.getPosDelta());
        if (packet.getPredictionType().equals(RewindType.VEHICLE)) {
            this.writeVehiclePrediction(buffer, helper, packet);
        }
        buffer.writeBoolean(packet.isOnGround());
        helper.writePlayerInputTick(buffer, packet.getTick());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CorrectPlayerMovePredictionPacket packet) {
        packet.setPredictionType(RewindType.from(buffer.readUnsignedByte()));
        packet.setPos(helper.readVector3f(buffer));
        packet.setPosDelta(helper.readVector3f(buffer));
        if (packet.getPredictionType().equals(RewindType.VEHICLE)) {
            this.readVehiclePrediction(buffer, helper, packet);
        }
        packet.setOnGround(buffer.readBoolean());
        packet.setTick(helper.readPlayerInputTick(buffer));
    }

    protected void writeVehiclePrediction(ByteBuf buffer, BedrockCodecHelper helper, CorrectPlayerMovePredictionPacket packet) {
        helper.writeVector2f(buffer, packet.getRotation());
    }

    protected void readVehiclePrediction(ByteBuf buffer, BedrockCodecHelper helper, CorrectPlayerMovePredictionPacket packet) {
        packet.setRotation(helper.readVector2f(buffer));
    }
}
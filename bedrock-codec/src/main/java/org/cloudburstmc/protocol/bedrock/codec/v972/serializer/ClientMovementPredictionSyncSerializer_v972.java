package org.cloudburstmc.protocol.bedrock.codec.v972.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v786.serializer.ClientMovementPredictionSyncSerializer_v786;
import org.cloudburstmc.protocol.bedrock.packet.ClientMovementPredictionSyncPacket;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientMovementPredictionSyncSerializer_v972 extends ClientMovementPredictionSyncSerializer_v786 {
    public static final ClientMovementPredictionSyncSerializer_v972 INSTANCE = new ClientMovementPredictionSyncSerializer_v972();

    @Override
    protected void writeMovementAttributes(ByteBuf buffer, BedrockCodecHelper helper, ClientMovementPredictionSyncPacket packet) {
        super.writeMovementAttributes(buffer, helper, packet);
        buffer.writeFloatLE(packet.getFrictionModifier());
        buffer.writeFloatLE(packet.getBounciness());
        buffer.writeFloatLE(packet.getAirDragModifier());
    }

    @Override
    protected void readMovementAttributes(ByteBuf buffer, BedrockCodecHelper helper, ClientMovementPredictionSyncPacket packet) {
        super.readMovementAttributes(buffer, helper, packet);
        packet.setFrictionModifier(buffer.readFloatLE());
        packet.setBounciness(buffer.readFloatLE());
        packet.setAirDragModifier(buffer.readFloatLE());
    }
}
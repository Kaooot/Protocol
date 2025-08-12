package org.cloudburstmc.protocol.bedrock.codec.v786.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v776.serializer.ClientMovementPredictionSyncSerializer_v776;
import org.cloudburstmc.protocol.bedrock.packet.ClientMovementPredictionSyncPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientMovementPredictionSyncSerializer_v786 extends ClientMovementPredictionSyncSerializer_v776 {
    public static final ClientMovementPredictionSyncSerializer_v786 INSTANCE = new ClientMovementPredictionSyncSerializer_v786();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientMovementPredictionSyncPacket packet) {
        super.serialize(buffer, helper, packet);
        buffer.writeBoolean(packet.isActorFlyingState());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientMovementPredictionSyncPacket packet) {
        super.deserialize(buffer, helper, packet);
        packet.setActorFlyingState(buffer.readBoolean());
    }
}
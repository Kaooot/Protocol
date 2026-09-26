package org.cloudburstmc.protocol.bedrock.codec.v407.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.positiontracking.PositionTrackingId;
import org.cloudburstmc.protocol.bedrock.packet.PositionTrackingDBClientRequestPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class PositionTrackingDBClientRequestSerializer_v407 implements BedrockPacketSerializer<PositionTrackingDBClientRequestPacket> {
    public static final PositionTrackingDBClientRequestSerializer_v407 INSTANCE = new PositionTrackingDBClientRequestSerializer_v407();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PositionTrackingDBClientRequestPacket packet) {
        buffer.writeByte(packet.getAction().ordinal());
        VarInts.writeInt(buffer, packet.getId().getValue());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PositionTrackingDBClientRequestPacket packet) {
        packet.setAction(PositionTrackingDBClientRequestPacket.Action.from(buffer.readByte()));
        packet.setId(new PositionTrackingId(VarInts.readInt(buffer)));
    }
}
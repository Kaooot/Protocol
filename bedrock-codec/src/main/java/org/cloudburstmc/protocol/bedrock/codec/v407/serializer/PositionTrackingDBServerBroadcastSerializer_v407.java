package org.cloudburstmc.protocol.bedrock.codec.v407.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.positiontracking.PositionTrackingId;
import org.cloudburstmc.protocol.bedrock.packet.PositionTrackingDBServerBroadcastPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class PositionTrackingDBServerBroadcastSerializer_v407 implements BedrockPacketSerializer<PositionTrackingDBServerBroadcastPacket> {
    public static final PositionTrackingDBServerBroadcastSerializer_v407 INSTANCE = new PositionTrackingDBServerBroadcastSerializer_v407();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PositionTrackingDBServerBroadcastPacket packet) {
        buffer.writeByte(packet.getAction().ordinal());
        VarInts.writeInt(buffer, packet.getId().getValue());
        helper.writeTag(buffer, packet.getPositionTrackingData());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PositionTrackingDBServerBroadcastPacket packet) {
        packet.setAction(PositionTrackingDBServerBroadcastPacket.Action.from(buffer.readByte()));
        packet.setId(new PositionTrackingId(VarInts.readInt(buffer)));
        packet.setPositionTrackingData(helper.readTag(buffer, NbtMap.class));
    }
}
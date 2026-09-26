package org.cloudburstmc.protocol.bedrock.codec.v800.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.location.CoordinatesLocation;
import org.cloudburstmc.protocol.bedrock.data.location.HiddenLocation;
import org.cloudburstmc.protocol.bedrock.data.location.PlayerLocationPacketType;
import org.cloudburstmc.protocol.bedrock.packet.PlayerLocationPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerLocationSerializer_v800 implements BedrockPacketSerializer<PlayerLocationPacket> {

    public static final PlayerLocationSerializer_v800 INSTANCE = new PlayerLocationSerializer_v800();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerLocationPacket packet) {
        buffer.writeIntLE(packet.getLocation() instanceof CoordinatesLocation ? 0 : 1);
        VarInts.writeLong(buffer, packet.getTargetActorID());
        if (packet.getLocation() instanceof CoordinatesLocation) {
            this.writeCoordinatesLocation(buffer, helper, packet, (CoordinatesLocation) packet.getLocation());
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerLocationPacket packet) {
        final PlayerLocationPacketType type = PlayerLocationPacketType.from(buffer.readIntLE());
        packet.setTargetActorID(VarInts.readLong(buffer));
        switch (type) {
            case PLAYER_LOCATION_COORDINATES:
                packet.setLocation(this.readCoordinatesLocation(buffer, helper, packet));
            case PLAYER_LOCATION_HIDE:
                packet.setLocation(new HiddenLocation());
            default:
                throw new IllegalStateException("Unknown PlayerLocationPacketType");
        }
    }

    protected void writeCoordinatesLocation(ByteBuf buffer, BedrockCodecHelper helper, PlayerLocationPacket packet, CoordinatesLocation coordinatesLocation) {
        helper.writeVector3f(buffer, coordinatesLocation.getPosition());
    }

    protected CoordinatesLocation readCoordinatesLocation(ByteBuf buffer, BedrockCodecHelper helper, PlayerLocationPacket packet) {
        final CoordinatesLocation coordinatesLocation = new CoordinatesLocation();
        coordinatesLocation.setPosition(helper.readVector3f(buffer));
        return coordinatesLocation;
    }
}
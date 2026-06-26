package org.cloudburstmc.protocol.bedrock.codec.v800.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.payload.location.CoordinatesLocation;
import org.cloudburstmc.protocol.bedrock.data.payload.location.HiddenLocation;
import org.cloudburstmc.protocol.bedrock.data.payload.location.PlayerLocation;
import org.cloudburstmc.protocol.bedrock.data.payload.location.PlayerLocationPacketType;
import org.cloudburstmc.protocol.bedrock.packet.PlayerLocationPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerLocationSerializer_v800 implements BedrockPacketSerializer<PlayerLocationPacket> {
    public static final PlayerLocationSerializer_v800 INSTANCE = new PlayerLocationSerializer_v800();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerLocationPacket packet) {
        buffer.writeIntLE(packet.getLocation().getType().ordinal());
        VarInts.writeLong(buffer, packet.getTargetActorID());
        this.writeLocationVariant(buffer, helper, packet.getLocation());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerLocationPacket packet) {
        final PlayerLocationPacketType type = PlayerLocationPacketType.from(buffer.readIntLE());
        packet.setTargetActorID(VarInts.readLong(buffer));
        packet.setLocation(this.readLocationVariant(buffer, helper, type));
    }

    protected void writeLocationVariant(ByteBuf buffer, BedrockCodecHelper helper, PlayerLocation location) {
        if (location.getType().equals(PlayerLocationPacketType.PLAYER_LOCATION_COORDINATES)) {
            this.writeCoordinatesLocation(buffer, helper, (CoordinatesLocation) location);
        }
    }

    protected PlayerLocation readLocationVariant(ByteBuf buffer, BedrockCodecHelper helper, PlayerLocationPacketType type) {
        switch (type) {
            case PLAYER_LOCATION_COORDINATES:
                return this.readCoordinatesLocation(buffer, helper);
            case PLAYER_LOCATION_HIDE:
                return this.readHiddenLocation(buffer, helper);
            default:
                throw new IllegalStateException("Unknown PlayerLocationPacketType");
        }
    }

    protected void writeCoordinatesLocation(ByteBuf buffer, BedrockCodecHelper helper, CoordinatesLocation coordinatesLocation) {
        helper.writeVector3f(buffer, coordinatesLocation.getPosition());
    }

    protected CoordinatesLocation readCoordinatesLocation(ByteBuf buffer, BedrockCodecHelper helper) {
        final CoordinatesLocation coordinatesLocation = new CoordinatesLocation();
        coordinatesLocation.setPosition(helper.readVector3f(buffer));
        return coordinatesLocation;
    }

    protected void writeHiddenLocation(ByteBuf buffer, BedrockCodecHelper helper, HiddenLocation hiddenLocation) {

    }

    protected HiddenLocation readHiddenLocation(ByteBuf buffer, BedrockCodecHelper helper) {
        return new HiddenLocation();
    }
}
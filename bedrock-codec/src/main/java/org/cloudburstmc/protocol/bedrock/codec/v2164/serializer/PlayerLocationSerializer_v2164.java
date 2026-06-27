package org.cloudburstmc.protocol.bedrock.codec.v2164.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v800.serializer.PlayerLocationSerializer_v800;
import org.cloudburstmc.protocol.bedrock.data.payload.location.CoordinatesLocation;
import org.cloudburstmc.protocol.bedrock.data.payload.location.HiddenLocation;
import org.cloudburstmc.protocol.bedrock.data.payload.location.PlayerLocation;
import org.cloudburstmc.protocol.bedrock.data.payload.location.PlayerLocationPacketType;
import org.cloudburstmc.protocol.bedrock.packet.PlayerLocationPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerLocationSerializer_v2164 extends PlayerLocationSerializer_v800 {
    public static final PlayerLocationSerializer_v2164 INSTANCE = new PlayerLocationSerializer_v2164();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerLocationPacket packet) {
        VarInts.writeLong(buffer, packet.getTargetActorID());
        this.writeLocationVariant(buffer, helper, packet.getLocation());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerLocationPacket packet) {
        packet.setTargetActorID(VarInts.readLong(buffer));
        packet.setLocation(this.readLocationVariant(buffer, helper, null));
    }

    @Override
    protected void writeLocationVariant(ByteBuf buffer, BedrockCodecHelper helper, PlayerLocation location) {
        VarInts.writeUnsignedInt(buffer, location.getType().ordinal());
        if (location.getType().equals(PlayerLocationPacketType.PLAYER_LOCATION_COORDINATES)) {
            this.writeCoordinatesLocation(buffer, helper, (CoordinatesLocation) location);
        }
    }

    @Override
    protected PlayerLocation readLocationVariant(ByteBuf buffer, BedrockCodecHelper helper, PlayerLocationPacketType type) {
        type = PlayerLocationPacketType.from(VarInts.readUnsignedInt(buffer));
        switch (type) {
            case PLAYER_LOCATION_COORDINATES:
                return this.readCoordinatesLocation(buffer, helper);
            case PLAYER_LOCATION_HIDE:
                return this.readHiddenLocation(buffer, helper);
            default:
                throw new IllegalStateException("Unknown PlayerLocationPacketType");
        }
    }

    @Override
    protected void writeCoordinatesLocation(ByteBuf buffer, BedrockCodecHelper helper, CoordinatesLocation coordinatesLocation) {
        VarInts.writeInt(buffer, 0);
        helper.writeVector3f(buffer, coordinatesLocation.getPosition());
    }

    @Override
    protected CoordinatesLocation readCoordinatesLocation(ByteBuf buffer, BedrockCodecHelper helper) {
        VarInts.readInt(buffer);
        final CoordinatesLocation coordinatesLocation = new CoordinatesLocation();
        coordinatesLocation.setPosition(helper.readVector3f(buffer));
        return coordinatesLocation;
    }

    @Override
    protected void writeHiddenLocation(ByteBuf buffer, BedrockCodecHelper helper, HiddenLocation hiddenLocation) {
        VarInts.writeInt(buffer, 0);
    }

    @Override
    protected HiddenLocation readHiddenLocation(ByteBuf buffer, BedrockCodecHelper helper) {
        VarInts.readInt(buffer);
        return new HiddenLocation();
    }
}
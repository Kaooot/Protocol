package org.cloudburstmc.protocol.bedrock.codec.v2168.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v800.serializer.PlayerLocationSerializer_v800;
import org.cloudburstmc.protocol.bedrock.data.location.CoordinatesLocation;
import org.cloudburstmc.protocol.bedrock.data.location.HiddenLocation;
import org.cloudburstmc.protocol.bedrock.data.location.PlayerLocationData;
import org.cloudburstmc.protocol.bedrock.data.location.PlayerLocationPacketType;
import org.cloudburstmc.protocol.bedrock.packet.PlayerLocationPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerLocationSerializer_v2168 extends PlayerLocationSerializer_v800 {
    public static final PlayerLocationSerializer_v2168 INSTANCE = new PlayerLocationSerializer_v2168();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerLocationPacket packet) {
        VarInts.writeLong(buffer, packet.getTargetActorID());
        this.writeLocation(buffer, helper, packet);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerLocationPacket packet) {
        packet.setTargetActorID(VarInts.readLong(buffer));
        this.readLocation(buffer, helper, packet);
    }

    protected void writeLocation(ByteBuf buffer, BedrockCodecHelper helper, PlayerLocationPacket packet) {
        final PlayerLocationData location = packet.getLocation();
        final PlayerLocationPacketType type = location.getType();
        VarInts.writeUnsignedInt(buffer, type.ordinal());
        VarInts.writeInt(buffer, type.ordinal());
        switch (type) {
            case PLAYER_LOCATION_COORDINATES:
                this.writeCoordinatesLocation(buffer, helper, packet, (CoordinatesLocation) location);
                break;
            case PLAYER_LOCATION_HIDE:
                break;
        }
    }

    protected void readLocation(ByteBuf buffer, BedrockCodecHelper helper, PlayerLocationPacket packet) {
        final PlayerLocationPacketType type = PlayerLocationPacketType.from(VarInts.readUnsignedInt(buffer));
        VarInts.readInt(buffer);
        switch (type) {
            case PLAYER_LOCATION_COORDINATES:
                packet.setLocation(this.readCoordinatesLocation(buffer, helper, packet));
                break;
            case PLAYER_LOCATION_HIDE:
                packet.setLocation(new HiddenLocation());
                break;
        }
    }
}
package org.cloudburstmc.protocol.bedrock.codec.v2168.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.VariantCodec;
import org.cloudburstmc.protocol.bedrock.codec.v800.serializer.PlayerLocationSerializer_v800;
import org.cloudburstmc.protocol.bedrock.data.location.CoordinatesLocation;
import org.cloudburstmc.protocol.bedrock.data.location.HiddenLocation;
import org.cloudburstmc.protocol.bedrock.data.location.PlayerLocationPacketType;
import org.cloudburstmc.protocol.bedrock.packet.PlayerLocationPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerLocationSerializer_v2168 extends PlayerLocationSerializer_v800 {
    public static final PlayerLocationSerializer_v2168 INSTANCE = new PlayerLocationSerializer_v2168();

    protected VariantCodec<PlayerLocationPacket> locationVariant = VariantCodec.<PlayerLocationPacketType, PlayerLocationPacket>builder(PlayerLocationPacketType::ordinal)
            .prefix(
                    (buffer, helper, packet, value) -> VarInts.writeInt(buffer, value),
                    (buffer, helper, packet) -> VarInts.readInt(buffer)
            )
            .add(
                    PlayerLocationPacketType.PLAYER_LOCATION_COORDINATES,
                    CoordinatesLocation.class,
                    this::writeCoordinatesLocation,
                    this::readCoordinatesLocation
            )
            .add(
                    PlayerLocationPacketType.PLAYER_LOCATION_HIDE,
                    HiddenLocation.class
            )
            .build();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerLocationPacket packet) {
        VarInts.writeLong(buffer, packet.getTargetActorID());
        this.locationVariant.write(buffer, helper, packet, packet.getLocation());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerLocationPacket packet) {
        packet.setTargetActorID(VarInts.readLong(buffer));
        packet.setLocation(this.locationVariant.read(buffer, helper, packet));
    }
}
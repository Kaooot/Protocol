package org.cloudburstmc.protocol.bedrock.codec.v2225.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.actor.passenger.PassengerOfBlockArguments;
import org.cloudburstmc.protocol.bedrock.packet.SetPassengerOfBlockPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SetPassengerOfBlockSerializer_v2225 implements BedrockPacketSerializer<SetPassengerOfBlockPacket> {
    public static final SetPassengerOfBlockSerializer_v2225 INSTANCE = new SetPassengerOfBlockSerializer_v2225();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SetPassengerOfBlockPacket packet) {
        VarInts.writeLong(buffer, packet.getPassenger());
        helper.writeOptionalNull(buffer, packet.getPassengerOfBlockData(), helper::writePassengerOfBlockArguments);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SetPassengerOfBlockPacket packet) {
        packet.setPassenger(VarInts.readLong(buffer));
        packet.setPassengerOfBlockData(helper.readOptional(buffer, null, helper::readPassengerOfBlockArguments));
    }
}
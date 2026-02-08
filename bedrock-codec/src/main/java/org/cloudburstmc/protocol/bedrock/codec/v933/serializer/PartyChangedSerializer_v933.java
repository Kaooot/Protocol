package org.cloudburstmc.protocol.bedrock.codec.v933.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.PartyChangedPacket;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PartyChangedSerializer_v933 implements BedrockPacketSerializer<PartyChangedPacket> {
    public static final PartyChangedSerializer_v933 INSTANCE = new PartyChangedSerializer_v933();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PartyChangedPacket packet) {
        helper.writeString(buffer, packet.getPartyId());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PartyChangedPacket packet) {
        packet.setPartyId(helper.readString(buffer));
    }
}
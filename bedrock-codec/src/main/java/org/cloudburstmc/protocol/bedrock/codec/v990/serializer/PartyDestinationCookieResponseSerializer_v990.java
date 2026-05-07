package org.cloudburstmc.protocol.bedrock.codec.v990.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.PartyDestinationCookieResponsePacket;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PartyDestinationCookieResponseSerializer_v990 implements BedrockPacketSerializer<PartyDestinationCookieResponsePacket> {
    public static final PartyDestinationCookieResponseSerializer_v990 INSTANCE = new PartyDestinationCookieResponseSerializer_v990();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PartyDestinationCookieResponsePacket packet) {
        helper.writeString(buffer, packet.getCookie());
        buffer.writeBoolean(packet.isAccepted());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PartyDestinationCookieResponsePacket packet) {
        packet.setCookie(helper.readString(buffer));
        packet.setAccepted(buffer.readBoolean());
    }
}
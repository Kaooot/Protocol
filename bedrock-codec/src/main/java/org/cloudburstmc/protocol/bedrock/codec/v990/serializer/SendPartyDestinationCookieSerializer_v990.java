package org.cloudburstmc.protocol.bedrock.codec.v990.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.payload.party.PartyDestinationCookieIntent;
import org.cloudburstmc.protocol.bedrock.packet.SendPartyDestinationCookiePacket;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SendPartyDestinationCookieSerializer_v990 implements BedrockPacketSerializer<SendPartyDestinationCookiePacket> {
    public static final SendPartyDestinationCookieSerializer_v990 INSTANCE = new SendPartyDestinationCookieSerializer_v990();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SendPartyDestinationCookiePacket packet) {
        helper.writeString(buffer, packet.getCookie());
        helper.writeString(buffer, packet.getIntent().getId());
        helper.writeString(buffer, packet.getDestinationName());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SendPartyDestinationCookiePacket packet) {
        packet.setCookie(helper.readString(buffer));
        packet.setIntent(PartyDestinationCookieIntent.from(helper.readString(buffer)));
        packet.setDestinationName(helper.readString(buffer));
    }
}
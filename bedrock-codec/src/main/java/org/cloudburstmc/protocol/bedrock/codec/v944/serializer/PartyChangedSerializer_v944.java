package org.cloudburstmc.protocol.bedrock.codec.v944.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.payload.party.PlayerPartyInfo;
import org.cloudburstmc.protocol.bedrock.packet.PartyChangedPacket;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PartyChangedSerializer_v944 implements BedrockPacketSerializer<PartyChangedPacket> {
    public static final PartyChangedSerializer_v944 INSTANCE = new PartyChangedSerializer_v944();

    protected static final int MAX_PARTY_ID_LENGTH = 49;

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PartyChangedPacket packet) {
        this.writePlayerPartyInfo(buffer, helper, packet.getPartyInfo());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PartyChangedPacket packet) {
        packet.setPartyInfo(this.readPlayerPartyInfo(buffer, helper));
    }

    protected void writePlayerPartyInfo(ByteBuf buffer, BedrockCodecHelper helper, PlayerPartyInfo info) {
        helper.writeOptionalNull(buffer, info.getPartyId(), helper::writeString);
    }

    protected PlayerPartyInfo readPlayerPartyInfo(ByteBuf buffer, BedrockCodecHelper helper) {
        final PlayerPartyInfo info = new PlayerPartyInfo();
        info.setPartyId(helper.readOptional(buffer, null, (buf, codecHelper) -> codecHelper.readStringMaxLen(buf, MAX_PARTY_ID_LENGTH)));
        return info;
    }
}
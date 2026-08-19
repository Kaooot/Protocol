package org.cloudburstmc.protocol.bedrock.codec.v975.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v944.serializer.PartyChangedSerializer_v944;
import org.cloudburstmc.protocol.bedrock.data.payload.party.PlayerPartyInfo;
import org.cloudburstmc.protocol.bedrock.packet.PartyChangedPacket;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PartyChangedSerializer_v975 extends PartyChangedSerializer_v944 {
    public static final PartyChangedSerializer_v975 INSTANCE = new PartyChangedSerializer_v975();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PartyChangedPacket packet) {
        helper.writeOptionalNull(buffer, packet.getPartyInfo(), this::writePlayerPartyInfo);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PartyChangedPacket packet) {
        packet.setPartyInfo(helper.readOptional(buffer, null, this::readPlayerPartyInfo));
    }

    @Override
    protected void writePlayerPartyInfo(ByteBuf buffer, BedrockCodecHelper helper, PlayerPartyInfo info) {
        helper.writeString(buffer, info.getPartyId());
        buffer.writeBoolean(info.isPartyLeader());
    }

    @Override
    protected PlayerPartyInfo readPlayerPartyInfo(ByteBuf buffer, BedrockCodecHelper helper) {
        final PlayerPartyInfo info = new PlayerPartyInfo();
        info.setPartyId(helper.readStringMaxLen(buffer, MAX_PARTY_ID_LENGTH));
        info.setPartyLeader(buffer.readBoolean());
        return info;
    }
}
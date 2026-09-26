package org.cloudburstmc.protocol.bedrock.codec.v630.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.ShowStoreOfferSerializer_v291;
import org.cloudburstmc.protocol.bedrock.data.misc.ShowStoreOfferRedirectType;
import org.cloudburstmc.protocol.bedrock.packet.ShowStoreOfferPacket;

import java.util.UUID;

public class ShowStoreOfferSerializer_v630 extends ShowStoreOfferSerializer_v291 {
    public static final ShowStoreOfferSerializer_v630 INSTANCE = new ShowStoreOfferSerializer_v630();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ShowStoreOfferPacket packet) {
        helper.writeString(buffer, packet.getOfferId().toString());
        buffer.writeByte(packet.getRedirectType().ordinal());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ShowStoreOfferPacket packet) {
        packet.setOfferId(UUID.fromString(helper.readString(buffer)));
        packet.setRedirectType(ShowStoreOfferRedirectType.from(buffer.readUnsignedByte()));
    }
}
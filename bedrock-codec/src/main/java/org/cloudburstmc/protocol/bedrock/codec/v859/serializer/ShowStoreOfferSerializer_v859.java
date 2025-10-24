package org.cloudburstmc.protocol.bedrock.codec.v859.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.StoreOfferRedirectType;
import org.cloudburstmc.protocol.bedrock.packet.ShowStoreOfferPacket;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShowStoreOfferSerializer_v859 implements BedrockPacketSerializer<ShowStoreOfferPacket> {
    public static final ShowStoreOfferSerializer_v859 INSTANCE = new ShowStoreOfferSerializer_v859();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ShowStoreOfferPacket packet) {
        helper.writeUuid(buffer, packet.getProductID());
        buffer.writeByte(packet.getRedirectType().ordinal());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ShowStoreOfferPacket packet) {
        packet.setProductID(helper.readUuid(buffer));
        packet.setRedirectType(StoreOfferRedirectType.values()[buffer.readUnsignedByte()]);
    }
}
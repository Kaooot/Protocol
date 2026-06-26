package org.cloudburstmc.protocol.bedrock.codec.v2164.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v729.serializer.TransferSerializer_v729;
import org.cloudburstmc.protocol.bedrock.packet.TransferPacket;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TransferSerializer_v2164 extends TransferSerializer_v729 {
    public static final TransferSerializer_v2164 INSTANCE = new TransferSerializer_v2164();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, TransferPacket packet) {
        super.serialize(buffer, helper, packet);
        helper.writeOptionalNull(buffer, packet.getGatheringsConfig(), helper::writeGatheringsConfig);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, TransferPacket packet) {
        super.deserialize(buffer, helper, packet);
        packet.setGatheringsConfig(helper.readOptional(buffer, null, helper::readGatheringsConfig));
    }
}
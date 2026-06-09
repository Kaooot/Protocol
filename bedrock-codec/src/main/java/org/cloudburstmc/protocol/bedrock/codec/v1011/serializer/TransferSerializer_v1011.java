package org.cloudburstmc.protocol.bedrock.codec.v1011.serializer;

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
public class TransferSerializer_v1011 extends TransferSerializer_v729 {
    public static final TransferSerializer_v1011 INSTANCE = new TransferSerializer_v1011();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, TransferPacket packet) {
        super.serialize(buffer, helper, packet);
        helper.writeGatheringsConfig(buffer, packet.getGatheringsConfig());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, TransferPacket packet) {
        super.deserialize(buffer, helper, packet);
        packet.setGatheringsConfig(helper.readGatheringsConfig(buffer));
    }
}
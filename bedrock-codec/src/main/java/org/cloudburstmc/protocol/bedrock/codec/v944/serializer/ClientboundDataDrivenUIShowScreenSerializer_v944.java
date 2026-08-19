package org.cloudburstmc.protocol.bedrock.codec.v944.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v924.serializer.ClientboundDataDrivenUIShowScreenSerializer_v924;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundDataDrivenUIShowScreenPacket;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientboundDataDrivenUIShowScreenSerializer_v944 extends ClientboundDataDrivenUIShowScreenSerializer_v924 {
    public static final ClientboundDataDrivenUIShowScreenSerializer_v944 INSTANCE = new ClientboundDataDrivenUIShowScreenSerializer_v944();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDataDrivenUIShowScreenPacket packet) {
        super.serialize(buffer, helper, packet);
        buffer.writeIntLE(packet.getFormId());
        helper.writeOptionalNull(buffer, packet.getDataInstanceId(),
                (buf, aHelper, dataInstanceId) -> buf.writeIntLE(dataInstanceId));
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDataDrivenUIShowScreenPacket packet) {
        super.deserialize(buffer, helper, packet);
        packet.setFormId(buffer.readIntLE());
        packet.setDataInstanceId(helper.readOptional(buffer, null, ByteBuf::readIntLE));
    }
}
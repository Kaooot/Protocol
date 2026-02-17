package org.cloudburstmc.protocol.bedrock.codec.v941.serializer;

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
public class ClientboundDataDrivenUIShowScreenSerializer_v941 extends ClientboundDataDrivenUIShowScreenSerializer_v924 {
    public static final ClientboundDataDrivenUIShowScreenSerializer_v941 INSTANCE = new ClientboundDataDrivenUIShowScreenSerializer_v941();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDataDrivenUIShowScreenPacket packet) {
        helper.writeString(buffer, packet.getScreenId());
        buffer.writeIntLE(packet.getFormId());
        helper.writeOptionalNull(buffer, packet.getDataInstanceId(),
                (buf, aHelper, dataInstanceId) -> buf.writeIntLE(dataInstanceId));
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDataDrivenUIShowScreenPacket packet) {
        packet.setScreenId(helper.readString(buffer));
        packet.setFormId(buffer.readIntLE());
        packet.setDataInstanceId(helper.readOptional(buffer, null, ByteBuf::readIntLE));
    }
}
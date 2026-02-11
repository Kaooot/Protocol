package org.cloudburstmc.protocol.bedrock.codec.v935.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundDataDrivenUICloseScreenPacket;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientboundDataDrivenUICloseScreenSerializer_v935 implements BedrockPacketSerializer<ClientboundDataDrivenUICloseScreenPacket> {
    public static final ClientboundDataDrivenUICloseScreenSerializer_v935 INSTANCE = new ClientboundDataDrivenUICloseScreenSerializer_v935();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDataDrivenUICloseScreenPacket packet) {
        helper.writeOptionalNull(buffer, packet.getFormId(),
                (buf, aHelper, formId) -> buf.writeIntLE(formId));
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientboundDataDrivenUICloseScreenPacket packet) {
        packet.setFormId(helper.readOptional(buffer, null, ByteBuf::readIntLE));
    }
}
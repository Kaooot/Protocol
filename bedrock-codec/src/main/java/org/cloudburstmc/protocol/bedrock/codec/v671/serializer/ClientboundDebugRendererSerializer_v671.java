package org.cloudburstmc.protocol.bedrock.codec.v671.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v428.serializer.ClientboundDebugRendererSerializer_v428;
import org.cloudburstmc.protocol.bedrock.data.PayloadType;

public class ClientboundDebugRendererSerializer_v671 extends ClientboundDebugRendererSerializer_v428 {
    public static final ClientboundDebugRendererSerializer_v671 INSTANCE = new ClientboundDebugRendererSerializer_v671();

    @Override
    protected PayloadType readPayloadType(ByteBuf buffer, BedrockCodecHelper helper) {
        return PayloadType.values()[buffer.readIntLE()];
    }

    @Override
    protected void writePayloadType(ByteBuf buffer, BedrockCodecHelper helper, PayloadType type) {
        buffer.writeIntLE(type.ordinal());
    }
}
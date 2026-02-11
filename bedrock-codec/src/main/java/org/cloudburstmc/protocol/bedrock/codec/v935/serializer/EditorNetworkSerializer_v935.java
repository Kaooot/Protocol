package org.cloudburstmc.protocol.bedrock.codec.v935.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v712.serializer.EditorNetworkSerializer_v712;
import org.cloudburstmc.protocol.bedrock.packet.EditorNetworkPacket;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EditorNetworkSerializer_v935 extends EditorNetworkSerializer_v712 {
    public static final EditorNetworkSerializer_v935 INSTANCE = new EditorNetworkSerializer_v935();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, EditorNetworkPacket packet) {
        buffer.writeBoolean(packet.isRouteToManager());
        helper.writeString(buffer, packet.getRawVariantName());
        helper.writeString(buffer, packet.getRawVariantData());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, EditorNetworkPacket packet) {
        packet.setRouteToManager(buffer.readBoolean());
        packet.setRawVariantName(helper.readString(buffer));
        packet.setRawVariantData(helper.readString(buffer));
    }
}
package org.cloudburstmc.protocol.bedrock.codec.v843.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.MapInfoRequestSerializer_v291;
import org.cloudburstmc.protocol.bedrock.data.map.MapPixel;
import org.cloudburstmc.protocol.bedrock.packet.MapInfoRequestPacket;

/**
 * @author Kaooot
 */
public class MapInfoRequestSerializer_v843 extends MapInfoRequestSerializer_v291 {
    public static final MapInfoRequestSerializer_v843 INSTANCE = new MapInfoRequestSerializer_v843();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, MapInfoRequestPacket packet) {
        super.serialize(buffer, helper, packet);

        helper.writeArray(buffer, packet.getClientPixelsList(), (buf, aHelper, pixel) -> {
            buf.writeIntLE(pixel.getPixel());
            buf.writeShortLE(pixel.getIndex());
        });
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, MapInfoRequestPacket packet) {
        super.deserialize(buffer, helper, packet);

        helper.readArray(buffer, packet.getClientPixelsList(), (buf, aHelper) -> {
            int pixel = buf.readIntLE();
            int index = buf.readUnsignedShortLE();
            return new MapPixel(pixel, index);
        }, 128 * 128); // There shouldn't be map with more pixels
    }
}
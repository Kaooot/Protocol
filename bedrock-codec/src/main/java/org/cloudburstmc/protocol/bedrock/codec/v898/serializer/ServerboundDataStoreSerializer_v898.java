package org.cloudburstmc.protocol.bedrock.codec.v898.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundDataStorePacket;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ServerboundDataStoreSerializer_v898 implements BedrockPacketSerializer<ServerboundDataStorePacket> {
    public static final ServerboundDataStoreSerializer_v898 INSTANCE = new ServerboundDataStoreSerializer_v898();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ServerboundDataStorePacket packet) {
        helper.writeDataStoreUpdate(buffer, packet.getUpdate());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ServerboundDataStorePacket packet) {
        packet.setUpdate(helper.readDataStoreUpdate(buffer));
    }
}
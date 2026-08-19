package org.cloudburstmc.protocol.bedrock.codec.v2187.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.RecordStartedPacket;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecordStartedSerializer_v2187 implements BedrockPacketSerializer<RecordStartedPacket> {
    public static final RecordStartedSerializer_v2187 INSTANCE = new RecordStartedSerializer_v2187();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, RecordStartedPacket packet) {
        helper.writeBlockPosition(buffer, packet.getBlockPosition());
        helper.writeServerSoundHandle(buffer, packet.getServerSoundHandle());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, RecordStartedPacket packet) {
        packet.setBlockPosition(helper.readBlockPosition(buffer));
        packet.setServerSoundHandle(helper.readServerSoundHandle(buffer));
    }
}
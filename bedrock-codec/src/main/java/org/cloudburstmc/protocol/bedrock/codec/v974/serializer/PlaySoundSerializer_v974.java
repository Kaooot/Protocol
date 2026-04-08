package org.cloudburstmc.protocol.bedrock.codec.v974.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.PlaySoundSerializer_v291;
import org.cloudburstmc.protocol.bedrock.data.ServerSoundHandle;
import org.cloudburstmc.protocol.bedrock.packet.PlaySoundPacket;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaySoundSerializer_v974 extends PlaySoundSerializer_v291 {
    public static final PlaySoundSerializer_v974 INSTANCE = new PlaySoundSerializer_v974();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlaySoundPacket packet) {
        super.serialize(buffer, helper, packet);
        helper.writeOptionalNull(buffer, packet.getServerSoundHandle(), this::writeServerSoundHandle);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlaySoundPacket packet) {
        super.deserialize(buffer, helper, packet);
        packet.setServerSoundHandle(helper.readOptional(buffer, null, this::readServerSoundHandle));
    }

    protected void writeServerSoundHandle(ByteBuf buffer, BedrockCodecHelper helper, ServerSoundHandle handle) {
        buffer.writeLongLE(handle.getServerSoundHandle());
    }

    protected ServerSoundHandle readServerSoundHandle(ByteBuf buffer, BedrockCodecHelper helper) {
        final long serverSoundHandle = buffer.readLongLE();
        return new ServerSoundHandle(serverSoundHandle);
    }
}
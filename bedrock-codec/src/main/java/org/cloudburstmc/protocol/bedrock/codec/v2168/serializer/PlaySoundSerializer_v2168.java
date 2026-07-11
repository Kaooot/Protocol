package org.cloudburstmc.protocol.bedrock.codec.v2168.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v975.serializer.PlaySoundSerializer_v975;
import org.cloudburstmc.protocol.bedrock.packet.PlaySoundPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaySoundSerializer_v2168 extends PlaySoundSerializer_v975 {
    public static final PlaySoundSerializer_v2168 INSTANCE = new PlaySoundSerializer_v2168();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlaySoundPacket packet) {
        helper.writeString(buffer, packet.getName());
        helper.writeBlockPosition(buffer, packet.getPosition().mul(8).toInt());
        buffer.writeFloatLE(packet.getVolume());
        buffer.writeFloatLE(packet.getPitch());
        VarInts.writeInt(buffer, packet.getLoopCount());
        helper.writeOptionalNull(buffer, packet.getServerSoundHandle(), helper::writeServerSoundHandle);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlaySoundPacket packet) {
        packet.setName(helper.readString(buffer));
        packet.setPosition(helper.readBlockPosition(buffer).toFloat().div(8));
        packet.setVolume(buffer.readFloatLE());
        packet.setPitch(buffer.readFloatLE());
        packet.setLoopCount(VarInts.readInt(buffer));
        packet.setServerSoundHandle(helper.readOptional(buffer, null, helper::readServerSoundHandle));
    }
}
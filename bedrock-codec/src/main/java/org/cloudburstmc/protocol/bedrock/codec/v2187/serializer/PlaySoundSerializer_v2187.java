package org.cloudburstmc.protocol.bedrock.codec.v2187.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v2168.serializer.PlaySoundSerializer_v2168;
import org.cloudburstmc.protocol.bedrock.packet.PlaySoundPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaySoundSerializer_v2187 extends PlaySoundSerializer_v2168 {
    public static final PlaySoundSerializer_v2187 INSTANCE = new PlaySoundSerializer_v2187();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlaySoundPacket packet) {
        helper.writeString(buffer, packet.getName());
        helper.writeBlockPosition(buffer, packet.getPosition().mul(8).toInt());
        buffer.writeFloatLE(packet.getVolume());
        buffer.writeFloatLE(packet.getPitch());
        VarInts.writeInt(buffer, packet.getLoopCount());
        buffer.writeBoolean(packet.isBypassListenerRangeCheck());
        helper.writeOptionalNull(buffer, packet.getServerSoundHandle(), helper::writeServerSoundHandle);
        helper.writeOptionalNull(buffer, packet.getPlaybackPositionSeconds(), ByteBuf::writeFloatLE);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlaySoundPacket packet) {
        packet.setName(helper.readString(buffer));
        packet.setPosition(helper.readBlockPosition(buffer).toFloat().div(8));
        packet.setVolume(buffer.readFloatLE());
        packet.setPitch(buffer.readFloatLE());
        packet.setLoopCount(VarInts.readInt(buffer));
        packet.setBypassListenerRangeCheck(buffer.readBoolean());
        packet.setServerSoundHandle(helper.readOptional(buffer, null, helper::readServerSoundHandle));
        packet.setPlaybackPositionSeconds(helper.readOptional(buffer, null, ByteBuf::readFloatLE));
    }
}
package org.cloudburstmc.protocol.bedrock.codec.v419.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.CameraShakeType;
import org.cloudburstmc.protocol.bedrock.packet.CameraShakePacket;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CameraShakeSerializer_v419 implements BedrockPacketSerializer<CameraShakePacket> {

    public static final CameraShakeSerializer_v419 INSTANCE = new CameraShakeSerializer_v419();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CameraShakePacket packet) {
        buffer.writeFloatLE(packet.getIntensity());
        buffer.writeFloatLE(packet.getSeconds());
        buffer.writeByte(packet.getShakeType().ordinal());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CameraShakePacket packet) {
        packet.setIntensity(buffer.readFloatLE());
        packet.setSeconds(buffer.readFloatLE());
        packet.setShakeType(CameraShakeType.values()[buffer.readByte()]);
    }
}

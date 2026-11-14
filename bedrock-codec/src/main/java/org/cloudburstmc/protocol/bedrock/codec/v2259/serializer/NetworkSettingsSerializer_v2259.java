package org.cloudburstmc.protocol.bedrock.codec.v2259.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v554.serializer.NetworkSettingsSerializer_v554;
import org.cloudburstmc.protocol.bedrock.data.connection.SctpRecoveryProfile;
import org.cloudburstmc.protocol.bedrock.packet.NetworkSettingsPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NetworkSettingsSerializer_v2259 extends NetworkSettingsSerializer_v554 {
    public static final NetworkSettingsSerializer_v2259 INSTANCE = new NetworkSettingsSerializer_v2259();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, NetworkSettingsPacket packet) {
        super.serialize(buffer, helper, packet);
        buffer.writeByte(packet.getSctpRecoveryProfile().ordinal());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, NetworkSettingsPacket packet) {
        super.deserialize(buffer, helper, packet);
        packet.setSctpRecoveryProfile(SctpRecoveryProfile.from(buffer.readUnsignedByte()));
    }
}
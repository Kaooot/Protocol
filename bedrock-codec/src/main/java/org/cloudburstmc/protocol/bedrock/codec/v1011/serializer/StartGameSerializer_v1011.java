package org.cloudburstmc.protocol.bedrock.codec.v1011.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v1001.serializer.StartGameSerializer_v1001;
import org.cloudburstmc.protocol.bedrock.packet.StartGamePacket;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StartGameSerializer_v1011 extends StartGameSerializer_v1001 {
    public static final StartGameSerializer_v1011 INSTANCE = new StartGameSerializer_v1011();

    @Override
    protected void writeBeforeNetworkPermissions(ByteBuf buffer, BedrockCodecHelper helper, StartGamePacket packet) {
        this.writeNetworkPermissions(buffer, helper, packet.getNetworkPermissions());
    }

    @Override
    protected void readBeforeNetworkPermissions(ByteBuf buffer, BedrockCodecHelper helper, StartGamePacket packet) {
        packet.setNetworkPermissions(this.readNetworkPermissions(buffer, helper));
    }
}
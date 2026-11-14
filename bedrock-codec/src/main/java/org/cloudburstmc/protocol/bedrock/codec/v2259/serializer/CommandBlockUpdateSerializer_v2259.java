package org.cloudburstmc.protocol.bedrock.codec.v2259.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v776.serializer.CommandBlockUpdateSerializer_v776;
import org.cloudburstmc.protocol.bedrock.packet.CommandBlockUpdatePacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommandBlockUpdateSerializer_v2259 extends CommandBlockUpdateSerializer_v776 {
    public static final CommandBlockUpdateSerializer_v2259 INSTANCE = new CommandBlockUpdateSerializer_v2259();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CommandBlockUpdatePacket packet) {
        super.serialize(buffer, helper, packet);
        buffer.writeBoolean(packet.isLastOutputIsRawMessage());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CommandBlockUpdatePacket packet) {
        super.deserialize(buffer, helper, packet);
        packet.setLastOutputIsRawMessage(buffer.readBoolean());
    }
}
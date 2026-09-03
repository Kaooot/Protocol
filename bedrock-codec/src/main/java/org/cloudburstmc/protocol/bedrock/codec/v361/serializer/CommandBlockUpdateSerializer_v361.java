package org.cloudburstmc.protocol.bedrock.codec.v361.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.CommandBlockUpdateSerializer_v291;
import org.cloudburstmc.protocol.bedrock.packet.CommandBlockUpdatePacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommandBlockUpdateSerializer_v361 extends CommandBlockUpdateSerializer_v291 {
    public static final CommandBlockUpdateSerializer_v361 INSTANCE = new CommandBlockUpdateSerializer_v361();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CommandBlockUpdatePacket packet) {
        super.deserialize(buffer, helper, packet);
        packet.setTickDelay(buffer.readIntLE());
        packet.setExecuteOnFirstTick(buffer.readBoolean());
}

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CommandBlockUpdatePacket packet) {
        super.deserialize(buffer, helper, packet);
        buffer.writeIntLE(packet.getTickDelay());
        buffer.writeBoolean(packet.isExecuteOnFirstTick());
    }
}
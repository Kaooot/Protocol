package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.packet.CommandRequestPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommandRequestSerializer_v291 implements BedrockPacketSerializer<CommandRequestPacket> {
    public static final CommandRequestSerializer_v291 INSTANCE = new CommandRequestSerializer_v291();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CommandRequestPacket packet) {
        helper.writeString(buffer, packet.getCommand());
        helper.writeCommandOrigin(buffer, packet.getCommandOrigin());
        buffer.writeBoolean(packet.isInternalSource());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CommandRequestPacket packet) {
        packet.setCommand(helper.readString(buffer));
        packet.setCommandOrigin(helper.readCommandOrigin(buffer));
        packet.setInternalSource(buffer.readBoolean());
    }
}

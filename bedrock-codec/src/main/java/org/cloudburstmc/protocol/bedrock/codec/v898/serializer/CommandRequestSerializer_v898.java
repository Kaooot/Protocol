package org.cloudburstmc.protocol.bedrock.codec.v898.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v567.serializer.CommandRequestSerializer_v567;
import org.cloudburstmc.protocol.bedrock.data.CurrentCmdVersion;
import org.cloudburstmc.protocol.bedrock.packet.CommandRequestPacket;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommandRequestSerializer_v898 extends CommandRequestSerializer_v567 {
    public static final CommandRequestSerializer_v898 INSTANCE = new CommandRequestSerializer_v898();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CommandRequestPacket packet) {
        helper.writeString(buffer, packet.getCommand());
        helper.writeCommandOrigin(buffer, packet.getCommandOrigin());
        buffer.writeBoolean(packet.isInternal());
        helper.writeString(buffer, packet.getVersion().getId());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CommandRequestPacket packet) {
        packet.setCommand(helper.readStringMaxLen(buffer, 512));
        packet.setCommandOrigin(helper.readCommandOrigin(buffer));
        packet.setInternal(buffer.readBoolean());
        packet.setVersion(CurrentCmdVersion.from(helper.readString(buffer)));
    }
}
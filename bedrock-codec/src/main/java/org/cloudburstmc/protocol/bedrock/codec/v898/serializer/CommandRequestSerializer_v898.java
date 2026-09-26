package org.cloudburstmc.protocol.bedrock.codec.v898.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v567.serializer.CommandRequestSerializer_v567;
import org.cloudburstmc.protocol.bedrock.data.command.CurrentCmdVersion;
import org.cloudburstmc.protocol.bedrock.packet.CommandRequestPacket;

public class CommandRequestSerializer_v898 extends CommandRequestSerializer_v567 {

    public static final CommandRequestSerializer_v898 INSTANCE = new CommandRequestSerializer_v898();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CommandRequestPacket packet) {
        helper.writeString(buffer, packet.getCommand());
        helper.writeCommandOriginData(buffer, packet.getOrigin());
        buffer.writeBoolean(packet.isInternal());
        helper.writeString(buffer, packet.getVersion().getSerializeName());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CommandRequestPacket packet) {
        packet.setCommand(helper.readString(buffer));
        packet.setOrigin(helper.readCommandOriginData(buffer));
        packet.setInternal(buffer.readBoolean());
        packet.setVersion(CurrentCmdVersion.fromName(helper.readString(buffer)));
    }
}
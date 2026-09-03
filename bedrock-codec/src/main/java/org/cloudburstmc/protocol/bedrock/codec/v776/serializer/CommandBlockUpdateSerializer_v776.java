package org.cloudburstmc.protocol.bedrock.codec.v776.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v361.serializer.CommandBlockUpdateSerializer_v361;
import org.cloudburstmc.protocol.bedrock.packet.CommandBlockUpdatePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommandBlockUpdateSerializer_v776 extends CommandBlockUpdateSerializer_v361 {
    public static final CommandBlockUpdateSerializer_v776 INSTANCE = new CommandBlockUpdateSerializer_v776();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CommandBlockUpdatePacket packet) {
        this.writeTargetVariant(buffer, helper, packet.getTarget());
        helper.writeString(buffer, packet.getCommand());
        helper.writeString(buffer, packet.getLastOutput());
        helper.writeString(buffer, packet.getName());
        helper.writeString(buffer, packet.getFilteredName());
        buffer.writeBoolean(packet.isTrackOutput());
        buffer.writeIntLE(packet.getTickDelay());
        buffer.writeBoolean(packet.isExecuteOnFirstTick());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CommandBlockUpdatePacket packet) {
        packet.setTarget(this.readTargetVariant(buffer, helper));
        packet.setCommand(helper.readString(buffer));
        packet.setLastOutput(helper.readString(buffer));
        packet.setName(helper.readString(buffer));
        packet.setFilteredName(helper.readString(buffer));
        packet.setTrackOutput(buffer.readBoolean());
        packet.setTickDelay(buffer.readIntLE());
        packet.setExecuteOnFirstTick(buffer.readBoolean());
    }
}
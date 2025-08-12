package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.CommandBlockMode;
import org.cloudburstmc.protocol.bedrock.packet.CommandBlockUpdatePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommandBlockUpdateSerializer_v291 implements BedrockPacketSerializer<CommandBlockUpdatePacket> {
    public static final CommandBlockUpdateSerializer_v291 INSTANCE = new CommandBlockUpdateSerializer_v291();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CommandBlockUpdatePacket packet) {
        buffer.writeBoolean(packet.isBlock());

        if (packet.isBlock()) {
            helper.writeBlockPosition(buffer, packet.getBlockPosition());
            VarInts.writeUnsignedInt(buffer, packet.getCommandBlockMode().ordinal());
            buffer.writeBoolean(packet.isRedstoneMode());
            buffer.writeBoolean(packet.isConditional());
        } else {
            VarInts.writeUnsignedLong(buffer, packet.getTargetRuntimeID());
        }

        helper.writeString(buffer, packet.getCommand());
        helper.writeString(buffer, packet.getLastOutput());
        helper.writeString(buffer, packet.getName());
        buffer.writeBoolean(packet.isTrackOutput());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CommandBlockUpdatePacket packet) {
        packet.setBlock(buffer.readBoolean());

        if (packet.isBlock()) {
            packet.setBlockPosition(helper.readBlockPosition(buffer));
            packet.setCommandBlockMode(CommandBlockMode.values()[VarInts.readUnsignedInt(buffer)]);
            packet.setRedstoneMode(buffer.readBoolean());
            packet.setConditional(buffer.readBoolean());
        } else {
            packet.setTargetRuntimeID(VarInts.readUnsignedLong(buffer));
        }

        packet.setCommand(helper.readString(buffer));
        packet.setLastOutput(helper.readString(buffer));
        packet.setName(helper.readString(buffer));
        packet.setTrackOutput(buffer.readBoolean());
    }
}

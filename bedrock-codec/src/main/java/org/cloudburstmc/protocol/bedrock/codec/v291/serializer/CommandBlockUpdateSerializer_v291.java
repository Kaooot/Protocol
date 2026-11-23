package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.command.BlockCommandData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandBlockMode;
import org.cloudburstmc.protocol.bedrock.data.command.CommandBlockUpdateTargetType;
import org.cloudburstmc.protocol.bedrock.data.command.EntityCommandTarget;
import org.cloudburstmc.protocol.bedrock.packet.CommandBlockUpdatePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommandBlockUpdateSerializer_v291 implements BedrockPacketSerializer<CommandBlockUpdatePacket> {
    public static final CommandBlockUpdateSerializer_v291 INSTANCE = new CommandBlockUpdateSerializer_v291();

    protected void writeTarget(ByteBuf buffer, BedrockCodecHelper helper, CommandBlockUpdatePacket packet) {
        final Object target = packet.getTarget();
        if (target instanceof EntityCommandTarget) {
            VarInts.writeUnsignedInt(buffer, CommandBlockUpdateTargetType.ENTITY.ordinal());
            this.writeEntityCommandTarget(buffer, helper, packet, (EntityCommandTarget) target);
        } else if (target instanceof BlockCommandData) {
            VarInts.writeUnsignedInt(buffer, CommandBlockUpdateTargetType.BLOCK.ordinal());
            this.writeBlockCommandData(buffer, helper, packet, (BlockCommandData) target);
        }
    }

    protected void readTarget(ByteBuf buffer, BedrockCodecHelper helper, CommandBlockUpdatePacket packet) {
        switch (CommandBlockUpdateTargetType.from(VarInts.readUnsignedInt(buffer))) {
            case ENTITY:
                packet.setTarget(this.readEntityCommandTarget(buffer, helper, packet));
                break;
            case BLOCK:
                packet.setTarget(this.readBlockCommandData(buffer, helper, packet));
                break;
        }
    }

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CommandBlockUpdatePacket packet) {
        this.writeTarget(buffer, helper, packet);
        helper.writeString(buffer, packet.getCommand());
        helper.writeString(buffer, packet.getLastOutput());
        helper.writeString(buffer, packet.getName());
        buffer.writeBoolean(packet.isTrackOutput());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CommandBlockUpdatePacket packet) {
        this.readTarget(buffer, helper, packet);
        packet.setCommand(helper.readString(buffer));
        packet.setLastOutput(helper.readString(buffer));
        packet.setName(helper.readString(buffer));
        packet.setTrackOutput(buffer.readBoolean());
    }

    protected void writeEntityCommandTarget(ByteBuf buffer, BedrockCodecHelper helper, CommandBlockUpdatePacket packet, EntityCommandTarget target) {
        VarInts.writeUnsignedLong(buffer, target.getTargetRuntimeID());
    }

    protected EntityCommandTarget readEntityCommandTarget(ByteBuf buffer, BedrockCodecHelper helper, CommandBlockUpdatePacket packet) {
        return new EntityCommandTarget(VarInts.readUnsignedLong(buffer));
    }

    protected void writeBlockCommandData(ByteBuf buffer, BedrockCodecHelper helper, CommandBlockUpdatePacket packet, BlockCommandData data) {
        helper.writeBlockPosition(buffer, data.getBlockPosition());
        VarInts.writeUnsignedInt(buffer, data.getCommandBlockMode().ordinal());
        buffer.writeBoolean(data.isRedstoneMode());
        buffer.writeBoolean(data.isConditional());
    }

    protected BlockCommandData readBlockCommandData(ByteBuf buffer, BedrockCodecHelper helper, CommandBlockUpdatePacket packet) {
        final BlockCommandData data = new BlockCommandData();
        data.setBlockPosition(helper.readBlockPosition(buffer));
        data.setCommandBlockMode(CommandBlockMode.from(VarInts.readUnsignedInt(buffer)));
        data.setRedstoneMode(buffer.readBoolean());
        data.setConditional(buffer.readBoolean());
        return data;
    }
}
package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.payload.command.*;
import org.cloudburstmc.protocol.bedrock.packet.CommandBlockUpdatePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommandBlockUpdateSerializer_v291 implements BedrockPacketSerializer<CommandBlockUpdatePacket> {
    public static final CommandBlockUpdateSerializer_v291 INSTANCE = new CommandBlockUpdateSerializer_v291();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CommandBlockUpdatePacket packet) {
        this.writeTargetVariant(buffer, helper, packet.getTarget());
        helper.writeString(buffer, packet.getCommand());
        helper.writeString(buffer, packet.getLastOutput());
        helper.writeString(buffer, packet.getName());
        buffer.writeBoolean(packet.isTrackOutput());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CommandBlockUpdatePacket packet) {
        packet.setTarget(this.readTargetVariant(buffer, helper));
        packet.setCommand(helper.readString(buffer));
        packet.setLastOutput(helper.readString(buffer));
        packet.setName(helper.readString(buffer));
        packet.setTrackOutput(buffer.readBoolean());
    }

    protected void writeTargetVariant(ByteBuf buffer, BedrockCodecHelper helper, CommandBlockUpdateTarget target) {
        VarInts.writeUnsignedInt(buffer, target.getType().ordinal());
        switch (target.getType()) {
            case ENTITY:
                this.writeEntityCommandTarget(buffer, helper, (EntityCommandTarget) target);
                break;
            case BLOCK:
                this.writeBlockCommandData(buffer, helper, (BlockCommandData) target);
                break;
        }
    }

    protected CommandBlockUpdateTarget readTargetVariant(ByteBuf buffer, BedrockCodecHelper helper) {
        final CommandBlockUpdateTargetType targetType = CommandBlockUpdateTargetType.from(VarInts.readUnsignedInt(buffer));
        switch (targetType) {
            case ENTITY:
                return this.readEntityCommandTarget(buffer, helper);
            case BLOCK:
                return this.readBlockCommandData(buffer, helper);
            default:
                throw new IllegalStateException("should never happen");
        }
    }

    protected void writeEntityCommandTarget(ByteBuf buffer, BedrockCodecHelper helper, EntityCommandTarget target) {
        VarInts.writeUnsignedLong(buffer, target.getTargetRuntimeID());
    }

    protected EntityCommandTarget readEntityCommandTarget(ByteBuf buffer, BedrockCodecHelper helper) {
        return new EntityCommandTarget(VarInts.readUnsignedLong(buffer));
    }

    protected void writeBlockCommandData(ByteBuf buffer, BedrockCodecHelper helper, BlockCommandData data) {
        helper.writeBlockPosition(buffer, data.getBlockPosition());
        VarInts.writeUnsignedInt(buffer, data.getCommandBlockMode().ordinal());
        buffer.writeBoolean(data.isRedstoneMode());
        buffer.writeBoolean(data.isConditional());
    }

    protected BlockCommandData readBlockCommandData(ByteBuf buffer, BedrockCodecHelper helper) {
        final BlockCommandData data = new BlockCommandData();
        data.setBlockPosition(helper.readBlockPosition(buffer));
        data.setCommandBlockMode(CommandBlockMode.from(VarInts.readUnsignedInt(buffer)));
        data.setRedstoneMode(buffer.readBoolean());
        data.setConditional(buffer.readBoolean());
        return data;
    }
}
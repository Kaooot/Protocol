package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.codec.VariantCodec;
import org.cloudburstmc.protocol.bedrock.data.command.BlockCommandData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandBlockMode;
import org.cloudburstmc.protocol.bedrock.data.command.CommandBlockUpdateTargetType;
import org.cloudburstmc.protocol.bedrock.data.command.EntityCommandTarget;
import org.cloudburstmc.protocol.bedrock.packet.CommandBlockUpdatePacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommandBlockUpdateSerializer_v291 implements BedrockPacketSerializer<CommandBlockUpdatePacket> {
    public static final CommandBlockUpdateSerializer_v291 INSTANCE = new CommandBlockUpdateSerializer_v291();

    protected final VariantCodec<CommandBlockUpdatePacket> targetVariant = VariantCodec.<CommandBlockUpdateTargetType, CommandBlockUpdatePacket>builder(CommandBlockUpdateTargetType::ordinal)
            .add(
                    CommandBlockUpdateTargetType.ENTITY,
                    EntityCommandTarget.class,
                    this::writeEntityCommandTarget,
                    this::readEntityCommandTarget
            )
            .add(
                    CommandBlockUpdateTargetType.BLOCK,
                    BlockCommandData.class,
                    this::writeBlockCommandData,
                    this::readBlockCommandData
            )
            .build();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CommandBlockUpdatePacket packet) {
        this.targetVariant.write(buffer, helper, packet, packet.getTarget());
        helper.writeString(buffer, packet.getCommand());
        helper.writeString(buffer, packet.getLastOutput());
        helper.writeString(buffer, packet.getName());
        buffer.writeBoolean(packet.isTrackOutput());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CommandBlockUpdatePacket packet) {
        packet.setTarget(this.targetVariant.read(buffer, helper, packet));
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
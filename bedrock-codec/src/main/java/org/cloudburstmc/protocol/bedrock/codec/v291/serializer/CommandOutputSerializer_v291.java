package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOutput;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOutputMessage;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOutputType;
import org.cloudburstmc.protocol.bedrock.packet.CommandOutputPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommandOutputSerializer_v291 implements BedrockPacketSerializer<CommandOutputPacket> {
    public static final CommandOutputSerializer_v291 INSTANCE = new CommandOutputSerializer_v291();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CommandOutputPacket packet) {
        helper.writeCommandOriginData(buffer, packet.getOriginData());
        this.writeCommandOutput(buffer, helper, packet.getOutput());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CommandOutputPacket packet) {
        packet.setOriginData(helper.readCommandOriginData(buffer));
        packet.setOutput(this.readCommandOutput(buffer, helper));
    }

    protected void writeCommandOutput(ByteBuf buffer, BedrockCodecHelper helper, CommandOutput output) {
        buffer.writeByte(output.getOutputType().ordinal());
        VarInts.writeUnsignedInt(buffer, output.getSuccessCount());
        helper.writeArray(buffer, output.getOutputMessages(), this::writeCommandOutputMessage);
        if (output.getOutputType().equals(CommandOutputType.DATA_SET)) {
            helper.writeString(buffer, output.getDataSet());
        }
    }

    protected CommandOutput readCommandOutput(ByteBuf buffer, BedrockCodecHelper helper) {
        final CommandOutput output = new CommandOutput();
        output.setOutputType(CommandOutputType.from(buffer.readUnsignedByte()));
        output.setSuccessCount(VarInts.readUnsignedInt(buffer));
        helper.readArray(buffer, output.getOutputMessages(), this::readCommandOutputMessage);
        if (output.getOutputType().equals(CommandOutputType.DATA_SET)) {
            output.setDataSet(helper.readString(buffer));
        }
        return output;
    }

    protected void writeCommandOutputMessage(ByteBuf buffer, BedrockCodecHelper helper, CommandOutputMessage message) {
        buffer.writeBoolean(message.isSuccessful());
        helper.writeString(buffer, message.getMessageID());
        helper.writeArray(buffer, message.getParameters(), helper::writeString);
    }

    protected CommandOutputMessage readCommandOutputMessage(ByteBuf buffer, BedrockCodecHelper helper) {
        final CommandOutputMessage message = new CommandOutputMessage();
        message.setSuccessful(buffer.readBoolean());
        message.setMessageID(helper.readString(buffer));
        helper.readArray(buffer, message.getParameters(), helper::readString);
        return message;
    }
}
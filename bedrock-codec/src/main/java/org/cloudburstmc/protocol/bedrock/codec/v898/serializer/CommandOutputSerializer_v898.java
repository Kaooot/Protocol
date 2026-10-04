package org.cloudburstmc.protocol.bedrock.codec.v898.serializer;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.CommandOutputSerializer_v291;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOutput;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOutputMessage;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOutputType;

public class CommandOutputSerializer_v898 extends CommandOutputSerializer_v291 {

    public static final CommandOutputSerializer_v898 INSTANCE = new CommandOutputSerializer_v898();

    @Override
    protected void writeCommandOutput(ByteBuf buffer, BedrockCodecHelper helper, CommandOutput output) {
        helper.writeString(buffer, output.getOutputType().getSerializeName());
        buffer.writeIntLE(output.getSuccessCount());
        helper.writeArray(buffer, output.getOutputMessages(), this::writeCommandOutputMessage);
        helper.writeOptionalNull(buffer, output.getDataSet(), helper::writeString);
    }

    @Override
    protected CommandOutput readCommandOutput(ByteBuf buffer, BedrockCodecHelper helper) {
        final CommandOutput output = new CommandOutput();
        output.setOutputType(CommandOutputType.fromName(helper.readString(buffer)));
        output.setSuccessCount(buffer.readIntLE());
        helper.readArray(buffer, output.getOutputMessages(), this::readCommandOutputMessage);
        output.setDataSet(helper.readOptional(buffer, null, helper::readString));
        return output;
    }

    @Override
    protected void writeCommandOutputMessage(ByteBuf buffer, BedrockCodecHelper helper, CommandOutputMessage message) {
        helper.writeString(buffer, message.getMessageID());
        buffer.writeBoolean(message.isSuccessful());
        helper.writeArray(buffer, message.getParameters(), helper::writeString);
    }

    @Override
    protected CommandOutputMessage readCommandOutputMessage(ByteBuf buffer, BedrockCodecHelper helper) {
        final CommandOutputMessage message = new CommandOutputMessage();
        message.setMessageID(helper.readString(buffer));
        message.setSuccessful(buffer.readBoolean());
        helper.readArray(buffer, message.getParameters(), helper::readString);
        return message;
    }
}
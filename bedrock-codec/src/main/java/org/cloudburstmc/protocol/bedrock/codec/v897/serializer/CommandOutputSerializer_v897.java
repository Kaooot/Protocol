package org.cloudburstmc.protocol.bedrock.codec.v897.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v291.serializer.CommandOutputSerializer_v291;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOutputMessage;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOutputType;
import org.cloudburstmc.protocol.bedrock.packet.CommandOutputPacket;

/**
 * @author Kaooot
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommandOutputSerializer_v897 extends CommandOutputSerializer_v291 {
    public static final CommandOutputSerializer_v897 INSTANCE = new CommandOutputSerializer_v897();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CommandOutputPacket packet) {
        helper.writeCommandOrigin(buffer, packet.getOriginData());
        helper.writeString(buffer, packet.getOutputType().getId());
        buffer.writeIntLE(packet.getSuccessCount());
        helper.writeArray(buffer, packet.getOutputMessages(), this::writeMessage);
        helper.writeOptionalNull(buffer, packet.getDataSet(), helper::writeString);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CommandOutputPacket packet) {
        packet.setOriginData(helper.readCommandOrigin(buffer));
        packet.setOutputType(CommandOutputType.from(helper.readString(buffer)));
        packet.setSuccessCount(buffer.readIntLE());
        helper.readArray(buffer, packet.getOutputMessages(), this::readMessage);
        packet.setDataSet(helper.readOptional(buffer, null, helper::readString));
    }

    @Override
    public void writeMessage(ByteBuf buffer, BedrockCodecHelper helper, CommandOutputMessage outputMessage) {
        helper.writeString(buffer, outputMessage.getMessageId());
        buffer.writeBoolean(outputMessage.isSuccessful());
        helper.writeArray(buffer, outputMessage.getParameters(), helper::writeString);
    }

    @Override
    public CommandOutputMessage readMessage(ByteBuf buffer, BedrockCodecHelper helper) {
        final String messageId = helper.readString(buffer);
        final boolean successful = buffer.readBoolean();
        final String[] parameters = helper.readArray(buffer, new String[0], helper::readString);
        return new CommandOutputMessage(successful, messageId, parameters);
    }
}
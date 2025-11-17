package org.cloudburstmc.protocol.bedrock.codec.v291.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOutputMessage;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOutputType;
import org.cloudburstmc.protocol.bedrock.packet.CommandOutputPacket;
import org.cloudburstmc.protocol.common.util.VarInts;

import static java.util.Objects.requireNonNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommandOutputSerializer_v291 implements BedrockPacketSerializer<CommandOutputPacket> {
    public static final CommandOutputSerializer_v291 INSTANCE = new CommandOutputSerializer_v291();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CommandOutputPacket packet) {
        helper.writeCommandOrigin(buffer, packet.getOriginData());
        buffer.writeByte(packet.getOutputType().ordinal());
        VarInts.writeUnsignedInt(buffer, packet.getSuccessCount());

        helper.writeArray(buffer, packet.getOutputMessages(), this::writeMessage);

        if (packet.getOutputType() == CommandOutputType.DATA_SET) {
            helper.writeString(buffer, packet.getDataSet());
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CommandOutputPacket packet) {
        packet.setOriginData(helper.readCommandOrigin(buffer));
        packet.setOutputType(CommandOutputType.values()[buffer.readUnsignedByte()]);
        packet.setSuccessCount(VarInts.readUnsignedInt(buffer));

        helper.readArray(buffer, packet.getOutputMessages(), this::readMessage);

        if (packet.getOutputType() == CommandOutputType.DATA_SET) {
            packet.setDataSet(helper.readString(buffer));
        }
    }

    public CommandOutputMessage readMessage(ByteBuf buffer, BedrockCodecHelper helper) {
        boolean internal = buffer.readBoolean();
        String messageId = helper.readString(buffer);
        String[] parameters = helper.readArray(buffer, new String[0], helper::readString);
        return new CommandOutputMessage(internal, messageId, parameters);
    }

    public void writeMessage(ByteBuf buffer, BedrockCodecHelper helper, CommandOutputMessage outputMessage) {
        requireNonNull(outputMessage, "CommandOutputMessage is null");

        buffer.writeBoolean(outputMessage.isSuccessful());
        helper.writeString(buffer, outputMessage.getMessageId());
        helper.writeArray(buffer, outputMessage.getParameters(), helper::writeString);
    }
}

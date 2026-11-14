package org.cloudburstmc.protocol.bedrock.codec.v2259.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v898.serializer.CommandOutputSerializer_v898;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOutputMessage;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommandOutputSerializer_v2259 extends CommandOutputSerializer_v898 {
    public static final CommandOutputSerializer_v2259 INSTANCE = new CommandOutputSerializer_v2259();

    @Override
    protected void writeCommandOutputMessage(ByteBuf buffer, BedrockCodecHelper helper, CommandOutputMessage message) {
        super.writeCommandOutputMessage(buffer, helper, message);
        helper.writeOptionalNull(buffer, message.getRawMessage(), helper::writeString);
    }

    @Override
    protected CommandOutputMessage readCommandOutputMessage(ByteBuf buffer, BedrockCodecHelper helper) {
        final CommandOutputMessage message = super.readCommandOutputMessage(buffer, helper);
        message.setRawMessage(helper.readOptional(buffer, null, helper::readString));
        return message;
    }
}
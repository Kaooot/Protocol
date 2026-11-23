package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.bedrock.data.command.CommandBlockUpdateTarget;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class CommandBlockUpdatePacket implements BedrockPacket {

    private CommandBlockUpdateTarget target;
    private String command;
    private String lastOutput;
    private String name;
    private String filteredName;
    private boolean trackOutput;
    private int tickDelay;
    private boolean executeOnFirstTick;
    @Added("26.70")
    private boolean lastOutputIsRawMessage;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.COMMAND_BLOCK_UPDATE;
    }

    @Override
    public CommandBlockUpdatePacket clone() {
        try {
            return (CommandBlockUpdatePacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}

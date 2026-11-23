package org.cloudburstmc.protocol.bedrock.data.command;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3i;

@Data
public class BlockCommandData implements CommandBlockUpdateTarget {

    @Override
    public CommandBlockUpdateTargetType getType() {
        return CommandBlockUpdateTargetType.BLOCK;
    }


    private Vector3i blockPosition;
    private CommandBlockMode commandBlockMode;
    private boolean redstoneMode;
    private boolean isConditional;
}
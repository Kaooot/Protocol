package org.cloudburstmc.protocol.bedrock.data.payload.command;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3i;

/**
 * @author Kaooot
 */
@Data
public class BlockCommandData implements CommandBlockUpdateTarget {

    private Vector3i blockPosition;
    private CommandBlockMode commandBlockMode;
    private boolean redstoneMode;
    private boolean isConditional;

    @Override
    public CommandBlockUpdateTargetType getType() {
        return CommandBlockUpdateTargetType.BLOCK;
    }
}
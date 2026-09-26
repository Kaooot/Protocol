package org.cloudburstmc.protocol.bedrock.data.command;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3i;

@Data
public class BlockCommandData {

    private Vector3i blockPosition;
    private CommandBlockMode commandBlockMode;
    private boolean redstoneMode;
    private boolean isConditional;
}
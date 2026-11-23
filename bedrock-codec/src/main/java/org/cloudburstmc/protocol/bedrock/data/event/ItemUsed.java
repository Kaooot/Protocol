package org.cloudburstmc.protocol.bedrock.data.event;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;

/**
 * Item used telemetry data. Presumably introduced for the brush
 */
@Data
@Added("1.21.0")
public class ItemUsed {

    private int itemId;
    private int itemAux;
    private int useMethod;
    private int count;
}
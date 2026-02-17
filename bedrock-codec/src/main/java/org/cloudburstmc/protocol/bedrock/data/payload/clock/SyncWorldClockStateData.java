package org.cloudburstmc.protocol.bedrock.data.payload.clock;

import lombok.Data;

/**
 * @author Kaooot
 */
@Data
public class SyncWorldClockStateData {

    private long clockId;
    private int time;
    private boolean isPaused;
}
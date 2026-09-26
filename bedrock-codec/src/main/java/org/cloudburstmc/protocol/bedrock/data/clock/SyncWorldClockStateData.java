package org.cloudburstmc.protocol.bedrock.data.clock;

import lombok.Data;

@Data
public class SyncWorldClockStateData {

    private long clockId;
    private int time;
    private boolean isPaused;
}
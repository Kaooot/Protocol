package org.cloudburstmc.protocol.bedrock.data.payload.clock;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;

import java.util.List;

@Data
public class SyncStateData implements ClockPayloadData {

    private final List<SyncWorldClockStateData> clockData = new ObjectArrayList<>();

    @Override
    public ClockPayloadDataType getType() {
        return ClockPayloadDataType.SYNC_STATE_DATA;
    }
}
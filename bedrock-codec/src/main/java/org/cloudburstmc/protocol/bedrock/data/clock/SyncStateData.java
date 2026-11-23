package org.cloudburstmc.protocol.bedrock.data.clock;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;

import java.util.List;

@Data
public class SyncStateData implements SyncWorldClocksPayload {

    private final List<SyncWorldClockStateData> clockData = new ObjectArrayList<>();

    @Override
    public SyncWorldClocksPayloadType getType() {
        return SyncWorldClocksPayloadType.SYNC_STATE_DATA;
    }
}
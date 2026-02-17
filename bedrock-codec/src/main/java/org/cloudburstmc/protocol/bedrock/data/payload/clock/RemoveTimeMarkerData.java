package org.cloudburstmc.protocol.bedrock.data.payload.clock;

import it.unimi.dsi.fastutil.longs.LongArrayList;
import lombok.Data;

import java.util.List;

@Data
public class RemoveTimeMarkerData implements ClockPayloadData {

    private long clockId;
    private final List<Long> timeMarkerIds = new LongArrayList();

    @Override
    public ClockPayloadDataType getType() {
        return ClockPayloadDataType.REMOVE_TIME_MARKER_DATA;
    }
}
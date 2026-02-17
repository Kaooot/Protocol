package org.cloudburstmc.protocol.bedrock.data.payload.clock;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;

import java.util.List;

@Data
public class AddTimeMarkerData implements ClockPayloadData {

    private long clockId;
    private final List<TimeMarkerData> timeMarkers = new ObjectArrayList<>();

    @Override
    public ClockPayloadDataType getType() {
        return ClockPayloadDataType.ADD_TIME_MARKER_DATA;
    }
}
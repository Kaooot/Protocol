package org.cloudburstmc.protocol.bedrock.data.clock;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;

import java.util.List;

@Data
public class RemoveTimeMarkerData implements SyncWorldClocksPayload {

    private long clockId;
    private final List<Long> timeMarkerIds = new ObjectArrayList<>();
}
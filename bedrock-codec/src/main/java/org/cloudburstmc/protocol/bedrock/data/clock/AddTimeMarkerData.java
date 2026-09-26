package org.cloudburstmc.protocol.bedrock.data.clock;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;

import java.util.List;

@Data
public class AddTimeMarkerData {

    private long clockId;
    private final List<TimeMarkerData> timeMarkers = new ObjectArrayList<>();
}
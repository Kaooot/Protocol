package org.cloudburstmc.protocol.bedrock.data.payload.clock;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;

import java.util.List;

/**
 * @author Kaooot
 */
@Data
public class WorldClockData {

    private long id;
    private String name;
    private int time;
    private boolean isPaused;
    private final List<TimeMarkerData> timeMarkers = new ObjectArrayList<>();
}
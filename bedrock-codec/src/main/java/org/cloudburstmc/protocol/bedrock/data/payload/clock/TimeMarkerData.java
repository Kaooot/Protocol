package org.cloudburstmc.protocol.bedrock.data.payload.clock;

import lombok.Data;

/**
 * @author Kaooot
 */
@Data
public class TimeMarkerData {

    private long id;
    private String name;
    private int time;
    private Integer period;
}
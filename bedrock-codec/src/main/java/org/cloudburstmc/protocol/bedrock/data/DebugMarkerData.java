package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3f;

/**
 * @author Kaooot
 */
@Data
public class DebugMarkerData {

    private String text;
    private Vector3f position;
    private int color;
    private long duration;
}
package org.cloudburstmc.protocol.bedrock.data.debug;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3f;

import java.awt.Color;

@Data
public class DebugMarkerData {

    private String text;
    private Vector3f position;
    private Color color;
    private long duration;
}
package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3f;

/**
 * @author Kaooot
 */
@Data
@ToString
public class PacketShapeData {
    private long networkId;
    private ScriptDebugShapeType shapeType;
    private Vector3f location;
    private Float scale = 1f;
    private Vector3f rotation;
    private Float timeLeftTotalSec;
    private Integer color;
    private String text;
    private Vector3f boxBound;
    private Vector3f endLocation;
    private Float arrowHeadLength;
    private Float arrowHeadRadius;
    private Integer numSegments;
}
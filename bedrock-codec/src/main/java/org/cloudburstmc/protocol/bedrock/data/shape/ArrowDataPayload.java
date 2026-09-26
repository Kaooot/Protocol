package org.cloudburstmc.protocol.bedrock.data.shape;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3f;

@Data
public class ArrowDataPayload {

    private Vector3f arrowEndLocation;
    private Float arrowHeadLength;
    private Float arrowHeadRadius;
    private Integer numSegments;
}
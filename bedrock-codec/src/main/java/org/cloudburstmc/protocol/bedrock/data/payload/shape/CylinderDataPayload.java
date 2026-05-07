package org.cloudburstmc.protocol.bedrock.data.payload.shape;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.protocol.bedrock.data.ExtraShapeDataType;

/**
 * @author Kaooot
 */
@Data
public class CylinderDataPayload implements DebugShapePayload {

    private Vector2f radiusX;
    private Vector2f radiusZ;
    private float height;
    private int numSegments;

    @Override
    public ExtraShapeDataType getType() {
        return ExtraShapeDataType.CYLINDER;
    }
}
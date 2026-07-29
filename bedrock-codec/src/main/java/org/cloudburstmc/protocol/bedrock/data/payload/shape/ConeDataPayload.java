package org.cloudburstmc.protocol.bedrock.data.payload.shape;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.protocol.bedrock.data.ExtraShapeDataType;

/**
 * @author Kaooot
 */
@Data
public class ConeDataPayload implements ExtraShapeDataPayload {

    private Vector2f radii;
    private float height;
    private int numSegments;

    @Override
    public ExtraShapeDataType getType() {
        return ExtraShapeDataType.CONE;
    }
}
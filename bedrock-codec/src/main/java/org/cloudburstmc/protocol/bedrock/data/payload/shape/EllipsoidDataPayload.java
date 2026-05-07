package org.cloudburstmc.protocol.bedrock.data.payload.shape;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.ExtraShapeDataType;

/**
 * @author Kaooot
 */
@Data
public class EllipsoidDataPayload implements DebugShapePayload {

    private Vector3f radii;
    private int segmentsPerAxis;

    @Override
    public ExtraShapeDataType getType() {
        return ExtraShapeDataType.ELLIPSOID;
    }
}
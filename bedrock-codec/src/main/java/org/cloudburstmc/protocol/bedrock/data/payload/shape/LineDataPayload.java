package org.cloudburstmc.protocol.bedrock.data.payload.shape;

import lombok.Data;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.ExtraShapeDataType;

/**
 * @author Kaooot
 */
@Data
@ToString
public class LineDataPayload implements DebugShapePayload {

    private Vector3f lineEndLocation;

    @Override
    public ExtraShapeDataType getType() {
        return ExtraShapeDataType.LINE;
    }
}
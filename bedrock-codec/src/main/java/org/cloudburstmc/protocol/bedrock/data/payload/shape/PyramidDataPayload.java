package org.cloudburstmc.protocol.bedrock.data.payload.shape;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.ExtraShapeDataType;

/**
 * @author Kaooot
 */
@Data
public class PyramidDataPayload implements DebugShapePayload {

    private float width;
    private Float depth;
    private float height;

    @Override
    public ExtraShapeDataType getType() {
        return ExtraShapeDataType.PYRAMID;
    }
}
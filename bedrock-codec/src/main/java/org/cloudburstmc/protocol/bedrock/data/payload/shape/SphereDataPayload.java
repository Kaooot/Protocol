package org.cloudburstmc.protocol.bedrock.data.payload.shape;

import lombok.Data;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.ExtraShapeDataType;

/**
 * @author Kaooot
 */
@Data
@ToString
public class SphereDataPayload implements ExtraShapeDataPayload {

    private Integer numSegments;

    @Override
    public ExtraShapeDataType getType() {
        return ExtraShapeDataType.SPHERE;
    }
}
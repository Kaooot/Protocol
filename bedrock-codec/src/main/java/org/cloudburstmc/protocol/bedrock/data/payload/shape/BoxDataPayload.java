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
public class BoxDataPayload implements ExtraShapeDataPayload {

    private Vector3f boxBound;

    @Override
    public ExtraShapeDataType getType() {
        return ExtraShapeDataType.BOX;
    }
}
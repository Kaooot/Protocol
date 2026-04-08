package org.cloudburstmc.protocol.bedrock.data.payload.shape;

import lombok.Data;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.Dimension;

/**
 * @author Kaooot
 */
@Data
@ToString
public class ShapeDataPayload {

    private long networkId;
    private ScriptPrimitiveShapeType shapeType;
    private Vector3f location;
    private Float scale;
    private Vector3f rotation;
    private Float totalTimeLeft;
    /**
     * @since v974
     */
    private Float maximumRenderDistance;
    private Integer color;
    private Dimension dimension;
    /**
     * @since v924
     */
    private Long attachedToEntityID;
    private DebugShapePayload extraShapeData;
}
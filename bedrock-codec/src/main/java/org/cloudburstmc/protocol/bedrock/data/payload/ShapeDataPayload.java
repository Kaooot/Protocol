package org.cloudburstmc.protocol.bedrock.data.payload;

import lombok.Data;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.Dimension;
import org.cloudburstmc.protocol.bedrock.data.ScriptDebugShapeType;

/**
 * @author Kaooot
 */
@Data
@ToString
public class ShapeDataPayload {

    private long networkId;
    private ScriptDebugShapeType shapeType;
    private Vector3f location;
    private Float scale;
    private Vector3f rotation;
    private Float totalTimeLeft;
    private Integer color;
    private Dimension dimension;
    private DebugShapePayload extraShapeData;
}
package org.cloudburstmc.protocol.bedrock.data.shape;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.datastore.NullType;
import org.cloudburstmc.protocol.bedrock.data.world.DimensionType;

@Data
public class PrimitiveShapeDataPayload {

    private long networkId;
    private ScriptPrimitiveShapeType shapeType;
    private Vector3f location;
    private Float scale;
    private Vector3f rotation;
    private Float totalTimeLeft;
    private Float maximumRenderDistance;
    private Integer color;
    private DimensionType dimension;
    private Long attachedToEntityID;
    private Object extraShapeData = new NullType();
}
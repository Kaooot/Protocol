package org.cloudburstmc.protocol.bedrock.data;

import lombok.Value;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.payload.common.DimensionType;

/**
 * @author Kaooot
 */
@Value
public class WorldPosition {

    Vector3f position;
    DimensionType dimensionType;
}
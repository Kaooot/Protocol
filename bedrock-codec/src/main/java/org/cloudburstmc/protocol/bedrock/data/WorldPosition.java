package org.cloudburstmc.protocol.bedrock.data;

import lombok.Value;
import org.cloudburstmc.math.vector.Vector3f;

/**
 * @author Kaooot
 */
@Value
public class WorldPosition {

    Vector3f position;
    Dimension dimensionType;
}
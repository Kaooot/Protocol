package org.cloudburstmc.protocol.bedrock.data.payload.map;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3i;

/**
 * @author Kaooot
 */
@Data
public class MapItemTrackedActorUniqueId {

    MapItemTrackedActorType type;
    Long entityID;
    Vector3i blockPosition;
}
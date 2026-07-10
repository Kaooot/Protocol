package org.cloudburstmc.protocol.bedrock.data.payload.map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector3i;

/**
 * @author Kaooot
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapItemTrackedActorUniqueId {

    MapItemTrackedActorType type;
    Long entityID;
    Vector3i blockPosition;
}
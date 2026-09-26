package org.cloudburstmc.protocol.bedrock.data.map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector3i;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MapItemTrackedActorUniqueId {

    private MapItemTrackedActorType type;
    private Long entityID;
    private Vector3i blockPosition;
}
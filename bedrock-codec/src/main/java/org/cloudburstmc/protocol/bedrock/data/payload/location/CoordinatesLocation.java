package org.cloudburstmc.protocol.bedrock.data.payload.location;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3f;

/**
 * @author Kaooot
 */
@Data
public class CoordinatesLocation implements PlayerLocation {

    private Vector3f position;

    @Override
    public PlayerLocationPacketType getType() {
        return PlayerLocationPacketType.PLAYER_LOCATION_COORDINATES;
    }
}
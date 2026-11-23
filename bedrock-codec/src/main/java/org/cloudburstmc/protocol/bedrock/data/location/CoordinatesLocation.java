package org.cloudburstmc.protocol.bedrock.data.location;

import lombok.Data;
import org.cloudburstmc.math.vector.Vector3f;

@Data
public class CoordinatesLocation implements PlayerLocationData {

    @Override
    public PlayerLocationPacketType getType() {
        return PlayerLocationPacketType.PLAYER_LOCATION_COORDINATES;
    }


    private Vector3f position;
}
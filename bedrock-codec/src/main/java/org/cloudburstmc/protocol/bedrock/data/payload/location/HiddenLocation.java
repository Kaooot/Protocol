package org.cloudburstmc.protocol.bedrock.data.payload.location;

import lombok.Data;

/**
 * @author Kaooot
 */
@Data
public class HiddenLocation implements PlayerLocation {

    @Override
    public PlayerLocationPacketType getType() {
        return PlayerLocationPacketType.PLAYER_LOCATION_HIDE;
    }
}
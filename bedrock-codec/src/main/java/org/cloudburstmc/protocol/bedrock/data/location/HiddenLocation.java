package org.cloudburstmc.protocol.bedrock.data.location;

import lombok.Data;

@Data
public class HiddenLocation implements PlayerLocationData {

    @Override
    public PlayerLocationPacketType getType() {
        return PlayerLocationPacketType.PLAYER_LOCATION_HIDE;
    }


}
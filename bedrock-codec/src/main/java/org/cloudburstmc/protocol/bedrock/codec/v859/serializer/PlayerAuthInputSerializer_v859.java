package org.cloudburstmc.protocol.bedrock.codec.v859.serializer;

import org.cloudburstmc.protocol.bedrock.codec.v766.serializer.PlayerAuthInputSerializer_v766;
import org.cloudburstmc.protocol.bedrock.data.player.input.PlayerActionType;
import org.cloudburstmc.protocol.common.util.TypeMap;

public class PlayerAuthInputSerializer_v859 extends PlayerAuthInputSerializer_v766 {

    public PlayerAuthInputSerializer_v859(TypeMap<PlayerActionType> playerActionTypes) {
        super(playerActionTypes);
        this.inputModeTypeMap = this.inputModeTypeMap.toBuilder()
                .remove(4)
                .build();
    }
}
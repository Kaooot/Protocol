package org.cloudburstmc.protocol.bedrock.codec.v2259.serializer;

import org.cloudburstmc.protocol.bedrock.codec.v2193.serializer.PlayerAuthInputSerializer_v2193;
import org.cloudburstmc.protocol.bedrock.data.player.input.InputMode;
import org.cloudburstmc.protocol.bedrock.data.player.input.PlayerActionType;
import org.cloudburstmc.protocol.common.util.TypeMap;

public class PlayerAuthInputSerializer_v2259 extends PlayerAuthInputSerializer_v2193 {

    public PlayerAuthInputSerializer_v2259(TypeMap<PlayerActionType> playerActionTypes) {
        super(playerActionTypes);
        this.inputModeTypeMap = this.inputModeTypeMap.toBuilder()
                .replace(5, InputMode.GAME_PAD_AND_MOUSE)
                .insert(6, InputMode.COUNT)
                .build();
    }
}
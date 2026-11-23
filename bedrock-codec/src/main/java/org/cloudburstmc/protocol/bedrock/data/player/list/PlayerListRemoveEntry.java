package org.cloudburstmc.protocol.bedrock.data.player.list;

import lombok.Data;

import java.util.UUID;

@Data
public class PlayerListRemoveEntry implements PlayerListEntry {

    @Override
    public PlayerListPacketType getType() {
        return PlayerListPacketType.REMOVE;
    }


    private UUID uuid;
}
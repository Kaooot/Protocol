package org.cloudburstmc.protocol.bedrock.data.payload.list;

import lombok.Data;

import java.util.UUID;

/**
 * @author Kaooot
 */
@Data
public class PlayerListRemoveEntry implements PlayerListEntry {

    private UUID uuid;

    @Override
    public PlayerListPacketType getPacketType() {
        return PlayerListPacketType.REMOVE;
    }
}
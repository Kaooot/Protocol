package org.cloudburstmc.protocol.bedrock.data.player.list;

import lombok.Data;

import java.util.UUID;

@Data
public class PlayerListRemoveEntry {

    private UUID uuid;
}
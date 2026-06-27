package org.cloudburstmc.protocol.bedrock.data.payload.list;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.BuildPlatform;
import org.cloudburstmc.protocol.bedrock.data.skin.Skin;

import java.util.UUID;

/**
 * @author Kaooot
 */
@Data
public class PlayerListAddEntry implements PlayerListEntry {

    private UUID uuid;
    private long actorUniqueID;
    private String playerName;
    private String xblXUID;
    private String platformOnlineID;
    private BuildPlatform buildPlatform;
    private Skin serializedSkin;
    private boolean isTeacher;
    private boolean isHost;
    private boolean isSubClient;
    private int playerColor;

    @Override
    public PlayerListPacketType getPacketType() {
        return PlayerListPacketType.ADD;
    }
}
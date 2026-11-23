package org.cloudburstmc.protocol.bedrock.data.player.list;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.bedrock.annotation.Removed;
import org.cloudburstmc.protocol.bedrock.data.connection.BuildPlatform;
import org.cloudburstmc.protocol.bedrock.data.skin.SerializedSkin;

import java.util.UUID;

@Data
public class PlayerListAddEntry {

    private UUID uuid;
    private long actorUniqueID;
    private String playerName;
    private String xblXUID;
    @Added("26.60")
    private String playFabID;
    private String platformOnlineID;
    private BuildPlatform buildPlatform;
    private SerializedSkin serializedSkin;
    private boolean isTeacher;
    private boolean isHost;
    @Deprecated
    @Removed("26.40")
    private boolean isTrustedSkin;
    private boolean isSubClient;
    private int playerColor;
}
package org.cloudburstmc.protocol.bedrock.data.player.list;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.connection.BuildPlatform;
import org.cloudburstmc.protocol.bedrock.data.skin.SerializedSkin;

import java.util.UUID;

@Data
public class PlayerListAddEntry {

    private UUID uuid;
    private long actorUniqueID;
    private String playerName;
    private String xblXUID;
    /**
     * @since v2207
     */
    private String playFabID;
    private String platformOnlineID;
    private BuildPlatform buildPlatform;
    private SerializedSkin serializedSkin;
    private boolean isTeacher;
    private boolean isHost;
    /**
     * @deprecated since v2168
     */
    private boolean isTrustedSkin;
    private boolean isSubClient;
    private int playerColor;
}
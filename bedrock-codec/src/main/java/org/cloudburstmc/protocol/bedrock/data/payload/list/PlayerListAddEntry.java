package org.cloudburstmc.protocol.bedrock.data.payload.list;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.BuildPlatform;
import org.cloudburstmc.protocol.bedrock.data.payload.skin.SerializedSkin;
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
    /**
     * @deprecated since v2164
     */
    private Skin skin;
    private SerializedSkin serializedSkin;
    private boolean isTeacher;
    private boolean isHost;
    /**
     * @deprecated since v2164
     */
    private boolean isTrustedSkin;
    private boolean isSubClient;
    private int playerColor;

    @Override
    public PlayerListPacketType getPacketType() {
        return PlayerListPacketType.ADD;
    }
}
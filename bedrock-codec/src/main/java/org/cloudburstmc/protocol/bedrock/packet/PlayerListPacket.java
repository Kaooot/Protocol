package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.BuildPlatform;
import org.cloudburstmc.protocol.bedrock.data.PlayerListPacketType;
import org.cloudburstmc.protocol.bedrock.data.skin.SerializedSkin;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.awt.*;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class PlayerListPacket implements BedrockPacket {
    private final List<Entry> entries = new ObjectArrayList<>();
    private PlayerListPacketType action;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    public BedrockPacketType getPacketType() {
        return BedrockPacketType.PLAYER_LIST;
    }


    @Data
    @ToString(doNotUseGetters = true)
    @EqualsAndHashCode(doNotUseGetters = true)
    public final static class Entry {
        private final UUID uuid;
        private long targetActorID;
        private String playerName;
        private String xblXUID;
        private String platformChatId;
        private BuildPlatform buildPlatform;
        private SerializedSkin skin;
        private boolean isTeacher;
        private boolean isHost;
        private boolean isTrustedSkin;
        private boolean isSubClient;
        private Color playerColor;
    }

    @Override
    public PlayerListPacket clone() {
        try {
            return (PlayerListPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}


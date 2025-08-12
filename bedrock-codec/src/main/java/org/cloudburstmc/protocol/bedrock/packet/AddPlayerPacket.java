package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.BuildPlatform;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.SerializedAbilitiesData;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataMap;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorLink;
import org.cloudburstmc.protocol.bedrock.data.actor.PropertySyncData;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class AddPlayerPacket implements BedrockPacket {
    private ActorDataMap actorData = new ActorDataMap();
    private List<ActorLink> actorLinks = new ObjectArrayList<>();
    private UUID uuid;
    private String playerName;
    private long targetActorID;
    private long targetRuntimeID;
    private String platformChatId;
    private Vector3f position;
    private Vector3f velocity;
    private Vector3f rotation;
    private ItemData carriedItem;
    private AdventureSettingsPacket adventureSettings = new AdventureSettingsPacket();
    private String deviceId;
    private BuildPlatform buildPlatform;
    private GameType playerGameType;

    /**
     * @since v534
     */
    private SerializedAbilitiesData abilitiesData = new SerializedAbilitiesData();
    /**
     * @since v557
     */
    private final PropertySyncData syncedProperties = new PropertySyncData();

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    public BedrockPacketType getPacketType() {
        return BedrockPacketType.ADD_PLAYER;
    }

    @Override
    public AddPlayerPacket clone() {
        try {
            return (AddPlayerPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}


package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.ability.SerializedAbilitiesData;
import org.cloudburstmc.protocol.bedrock.data.actor.PropertySyncData;
import org.cloudburstmc.protocol.bedrock.data.actor.link.ActorLink;
import org.cloudburstmc.protocol.bedrock.data.connection.BuildPlatform;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataMap;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.data.world.GameType;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class AddPlayerPacket implements BedrockPacket {

    private UUID uuid;
    private String playerName;
    /**
     * @deprecated since v534
     */
    private long targetActorID;
    private long targetRuntimeID;
    private String platformChatId;
    private Vector3f position;
    private Vector3f velocity;
    private Vector2f rotation;
    private float yHeadRotation;
    private ItemData carriedItem;
    /**
     * @since v503
     */
    private GameType playerGameType;
    private ActorDataMap entityData = new ActorDataMap();
    /**
     * @since v557
     */
    private PropertySyncData synchedProperties = new PropertySyncData();
    /**
     * @since v534
     */
    private SerializedAbilitiesData abilitiesData = new SerializedAbilitiesData();
    private final List<ActorLink> actorLinks = new ObjectArrayList<>();
    private String deviceId;
    /**
     * @since v388
     */
    private BuildPlatform buildPlatform;

    private AdventureSettingsPacket adventureSettings = new AdventureSettingsPacket();

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
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

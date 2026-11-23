package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.bedrock.annotation.Removed;
import org.cloudburstmc.protocol.bedrock.data.ability.SerializedAbilitiesData;
import org.cloudburstmc.protocol.bedrock.data.actor.PropertySyncData;
import org.cloudburstmc.protocol.bedrock.data.actor.link.ActorLink;
import org.cloudburstmc.protocol.bedrock.data.actor.passenger.PassengerOfBlockArguments;
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
    @Deprecated
    @Removed("1.19.10")
    private long targetActorID;
    private long targetRuntimeID;
    private String platformChatId;
    private Vector3f position;
    private Vector3f velocity;
    private Vector2f rotation;
    private float yHeadRotation;
    private ItemData carriedItem;
    @Added("1.18.30")
    private GameType playerGameType;
    private ActorDataMap entityData = new ActorDataMap();
    @Added("1.19.40")
    private PropertySyncData synchedProperties = new PropertySyncData();
    @Added("1.19.10")
    private SerializedAbilitiesData abilitiesData = new SerializedAbilitiesData();
    private final List<ActorLink> actorLinks = new ObjectArrayList<>();
    @Added("26.60")
    private PassengerOfBlockArguments passengerBlockData;
    private String deviceId;
    @Added("1.13.0")
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

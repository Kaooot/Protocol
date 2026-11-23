package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.bedrock.annotation.Removed;
import org.cloudburstmc.protocol.bedrock.data.actor.PropertySyncData;
import org.cloudburstmc.protocol.bedrock.data.actor.attribute.SyncedAttribute;
import org.cloudburstmc.protocol.bedrock.data.actor.link.ActorLink;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataMap;
import org.cloudburstmc.protocol.bedrock.data.actor.passenger.PassengerOfBlockArguments;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.List;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class AddActorPacket implements BedrockPacket {

    private long targetActorID;
    private long targetRuntimeID;
    @Deprecated
    @Removed("1.8.0")
    private int actorTypeDeprecated;
    @Added("1.8.0")
    private String actorType;
    private Vector3f position;
    private Vector3f velocity;
    private Vector2f rotation;
    private float yHeadRotation;
    @Added("1.19.10")
    private float yBodyRotation;
    private final List<SyncedAttribute> attributesList = new ObjectArrayList<>();
    private ActorDataMap actorData = new ActorDataMap();
    @Added("1.19.40")
    private PropertySyncData synchedProperties = new PropertySyncData();
    private final List<ActorLink> actorLinks = new ObjectArrayList<>();
    @Added("26.60")
    private PassengerOfBlockArguments passengerBlockData;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.ADD_ACTOR;
    }

    @Override
    public AddActorPacket clone() {
        try {
            return (AddActorPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
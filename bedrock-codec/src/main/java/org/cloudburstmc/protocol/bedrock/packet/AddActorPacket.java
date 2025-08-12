package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.AttributeData;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataMap;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorLink;
import org.cloudburstmc.protocol.bedrock.data.actor.PropertySyncData;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.List;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class AddActorPacket implements BedrockPacket {
    private List<AttributeData> attributesList = new ObjectArrayList<>();
    private ActorDataMap actorData = new ActorDataMap();
    private List<ActorLink> actorLinks = new ObjectArrayList<>();
    private long targetActorID;
    private long targetRuntimeID;
    private String actorType;
    private int entityType;
    private Vector3f position;
    private Vector3f velocity;
    private Vector2f rotation;
    /**
     * @since v534
     */
    private float headRotation;
    /**
     * @since v534
     */
    private float bodyRotation;
    /**
     * @since v557
     */
    private final PropertySyncData syncedProperties = new PropertySyncData();

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

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
package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.actor.PropertySyncData;
import org.cloudburstmc.protocol.bedrock.data.actor.attribute.SyncedAttribute;
import org.cloudburstmc.protocol.bedrock.data.actor.link.ActorLink;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorDataMap;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.List;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class AddActorPacket implements BedrockPacket {

    private long targetActorID;
    private long targetRuntimeID;
    /**
     * @deprecated since v313
     */
    private int actorTypeDeprecated;
    /**
     * @since v313
     */
    private String actorType;
    private Vector3f position;
    private Vector3f velocity;
    private Vector2f rotation;
    private float yHeadRotation;
    /**
     * @since v534
     */
    private float yBodyRotation;
    private final List<SyncedAttribute> attributesList = new ObjectArrayList<>();
    private ActorDataMap actorData = new ActorDataMap();
    /**
     * @since v557
     */
    private PropertySyncData synchedProperties = new PropertySyncData();
    private final List<ActorLink> actorLinks = new ObjectArrayList<>();

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
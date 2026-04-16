package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.actor.ActorFlags;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.Set;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class ClientMovementPredictionSyncPacket implements BedrockPacket {
    private long actorID;

    private final Set<ActorFlags> flags = new ObjectOpenHashSet<>();
    private Vector3f actorBoundingBox;

    private float movementSpeed;
    private float underwaterMovementSpeed;
    private float lavaMovementSpeed;
    private float jumpStrength;
    private float health;
    private float hunger;
    /**
     * @since v975
     */
    private float frictionModifier;
    /**
     * @since v975
     */
    private float bounciness;
    /**
     * @since v975
     */
    private float airDragModifier;
    private boolean actorFlyingState;


    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CLIENT_MOVEMENT_PREDICTION_SYNC;
    }

    @Override
    public ClientMovementPredictionSyncPacket clone() {
        try {
            return (ClientMovementPredictionSyncPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}


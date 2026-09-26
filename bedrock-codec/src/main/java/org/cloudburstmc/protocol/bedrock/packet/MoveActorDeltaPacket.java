package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.actor.MoveActorDeltaData;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.EnumSet;
import java.util.Set;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class MoveActorDeltaPacket implements BedrockPacket {

    /**
     * @deprecated since v2168
     */
    private final Set<Flag> flags = EnumSet.noneOf(Flag.class);
    private MoveActorDeltaData moveData = new MoveActorDeltaData();
    /**
     * @deprecated since v419
     */
    private int deltaX;
    /**
     * @deprecated since v419
     */
    private int deltaY;
    /**
     * @deprecated since v419
     */
    private int deltaZ;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.MOVE_ACTOR_DELTA;
    }

    public enum Flag {
        HAS_X,
        HAS_Y,
        HAS_Z,
        HAS_PITCH,
        HAS_YAW,
        HAS_HEAD_YAW,
        ON_GROUND,
        TELEPORTING,
        FORCE_MOVE_LOCAL_ENTITY
    }

    @Override
    public MoveActorDeltaPacket clone() {
        try {
            return (MoveActorDeltaPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
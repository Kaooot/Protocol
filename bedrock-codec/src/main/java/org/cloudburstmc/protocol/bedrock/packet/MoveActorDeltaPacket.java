package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.cloudburstmc.protocol.bedrock.data.payload.move.MoveActorDeltaData;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.util.EnumSet;
import java.util.Set;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
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

    public BedrockPacketType getPacketType() {
        return BedrockPacketType.MOVE_ACTOR_DELTA;
    }

    public String toString() {
        return "MoveActorDeltaPacket(data=" + this.moveData +
                ", flags=" + flags + ", delta=(" + deltaX + ", " + deltaY + ", " + deltaZ + "))";
    }

    /**
     * @deprecated since v2168
     */
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


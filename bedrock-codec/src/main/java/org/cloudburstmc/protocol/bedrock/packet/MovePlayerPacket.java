package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.payload.move.MovePlayerTeleportData;
import org.cloudburstmc.protocol.bedrock.data.payload.move.PositionMode;
import org.cloudburstmc.protocol.bedrock.data.payload.move.TeleportationCause;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class MovePlayerPacket implements BedrockPacket {
    private long playerRuntimeID;
    private Vector3f position;
    private Vector3f rotation;
    private PositionMode positionMode;
    private boolean onGround;
    private long ridingRuntimeID;
    private MovePlayerTeleportData teleportData;
    private long tick;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    public BedrockPacketType getPacketType() {
        return BedrockPacketType.MOVE_PLAYER;
    }

    @Override
    public MovePlayerPacket clone() {
        try {
            return (MovePlayerPacket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}


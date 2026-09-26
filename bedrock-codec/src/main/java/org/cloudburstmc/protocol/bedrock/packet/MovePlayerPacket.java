package org.cloudburstmc.protocol.bedrock.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.player.input.MovePlayerTeleportData;
import org.cloudburstmc.protocol.bedrock.data.player.input.PositionMode;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(doNotUseGetters = true)
@ToString(doNotUseGetters = true)
public class MovePlayerPacket implements BedrockPacket {

    private long playerRuntimeID;
    private Vector3f position;
    private Vector2f rotation;
    private float yHeadRotation;
    private PositionMode positionMode;
    private boolean onGround;
    private long ridingRuntimeID;
    private MovePlayerTeleportData teleportData;
    private long tick;

    @Override
    public final PacketSignal handle(BedrockPacketHandler handler) {
        return handler.handle(this);
    }

    @Override
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
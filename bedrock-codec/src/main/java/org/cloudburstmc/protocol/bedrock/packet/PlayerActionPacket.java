package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.data.PlayerActionType;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 36 (0x24)
 * The expected actions change depending on the ServerAuthMovementMode specified in the StartGamePacket.
 * See the PlayerActionType enum for details on which have differing behavior.
 * See also PlayerAuthInputPacket and InventoryTransactionPacket for similar types of player actions.
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class PlayerActionPacket implements BedrockPacket {
  private long PlayerRuntimeID;

  private PlayerActionType Action;

  private Vector3i BlockPosition;

  private Vector3i ResultPos;

  private int Face;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.PLAYER_ACTION;
  }

  @Override
  public PlayerActionPacket clone() {
    try {
      return (PlayerActionPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}

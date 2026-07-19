package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 306 (0x132)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class PlayerToggleCrafterSlotRequestPacket implements BedrockPacket {
  private int PosX;

  private int PosY;

  private int PosZ;

  private int SlotIndex;

  private boolean IsDisabled;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.PLAYER_TOGGLE_CRAFTER_SLOT_REQUEST;
  }

  @Override
  public PlayerToggleCrafterSlotRequestPacket clone() {
    try {
      return (PlayerToggleCrafterSlotRequestPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}

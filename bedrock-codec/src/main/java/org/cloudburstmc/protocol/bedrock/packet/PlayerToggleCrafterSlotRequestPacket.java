package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class PlayerToggleCrafterSlotRequestPacket implements BedrockPacket {
  private int posX;

  private int posY;

  private int posZ;

  private int slotIndex;

  private boolean isDisabled;

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

package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.InventoryOptions;
import org.cloudburstmc.protocol.common.PacketSignal;

@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class SetPlayerInventoryOptionsPacket implements BedrockPacket {
  private InventoryOptions inventoryOptions;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.SET_PLAYER_INVENTORY_OPTIONS;
  }

  @Override
  public SetPlayerInventoryOptionsPacket clone() {
    try {
      return (SetPlayerInventoryOptionsPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}

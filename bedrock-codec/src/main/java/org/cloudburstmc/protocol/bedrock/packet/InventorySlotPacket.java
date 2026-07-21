package org.cloudburstmc.protocol.bedrock.packet;

import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.FullContainerName;
import org.cloudburstmc.protocol.bedrock.data.NetworkItemStackDescriptor;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Packet ID: 50 (0x32)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class InventorySlotPacket implements BedrockPacket {
  private int containerId;

  private int slot;

  private FullContainerName fullContainerName;

  private NetworkItemStackDescriptor storageItem;

  private NetworkItemStackDescriptor item;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.INVENTORY_SLOT;
  }

  @Override
  public InventorySlotPacket clone() {
    try {
      return (InventorySlotPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}

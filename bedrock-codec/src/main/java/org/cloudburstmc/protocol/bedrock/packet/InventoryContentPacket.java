package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Override;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.FullContainerName;
import org.cloudburstmc.protocol.bedrock.data.NetworkItemStackDescriptor;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 49 (0x31)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class InventoryContentPacket implements BedrockPacket {
  private int containerId;

  private final List<NetworkItemStackDescriptor> slots = new ObjectArrayList<>();

  private FullContainerName fullContainerName;

  private NetworkItemStackDescriptor storageItem;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.INVENTORY_CONTENT;
  }

  @Override
  public InventoryContentPacket clone() {
    try {
      return (InventoryContentPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}

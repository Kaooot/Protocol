package org.cloudburstmc.protocol.bedrock.packet;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.AssertionError;
import java.lang.CloneNotSupportedException;
import java.lang.Object;
import java.lang.Override;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.cloudburstmc.protocol.bedrock.data.ItemStackLegacyRequestId;
import org.cloudburstmc.protocol.bedrock.data.LegacySetSlot;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 *
 * Packet ID: 30 (0x1e)
 */
@Data
@EqualsAndHashCode(
    doNotUseGetters = true
)
@ToString(
    doNotUseGetters = true
)
public class InventoryTransactionPacket implements BedrockPacket {
  private ItemStackLegacyRequestId legacyRequestID;

  private final List<LegacySetSlot> legacySetItemSlots = new ObjectArrayList<>();

  private Object transaction;

  @Override
  public final PacketSignal handle(BedrockPacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public BedrockPacketType getPacketType() {
    return BedrockPacketType.INVENTORY_TRANSACTION;
  }

  @Override
  public InventoryTransactionPacket clone() {
    try {
      return (InventoryTransactionPacket) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(e);
    }
  }
}

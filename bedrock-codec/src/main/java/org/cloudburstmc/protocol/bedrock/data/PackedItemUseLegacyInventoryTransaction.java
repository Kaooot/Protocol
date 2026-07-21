package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackedItemUseLegacyInventoryTransaction {
  private ItemStackLegacyRequestId legacyRequestID;

  private final List<LegacySetSlot> legacySetItemSlots = new ObjectArrayList<>();

  private ItemUseInventoryTransaction itemUseTransaction;
}

package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ItemStackResponseContainerInfo {
  private FullContainerName fullContainerName;

  private final List<ItemStackResponseSlotInfo> slots = new ObjectArrayList<>();
}

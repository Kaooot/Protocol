package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ItemStackResponseInfo {
  private ItemStackNetResult result;

  private ItemStackRequestId clientRequestId;

  private final List<ItemStackResponseContainerInfo> containers = new ObjectArrayList<>();
}

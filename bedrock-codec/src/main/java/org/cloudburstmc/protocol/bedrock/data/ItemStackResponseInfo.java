package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemStackResponseInfo {
  private ItemStackNetResult result;

  private ItemStackRequestId clientRequestId;

  private final List<ItemStackResponseContainerInfo> containers = new ObjectArrayList<>();
}

package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ItemStackRequestCraftResultsDeprecatedAction {
  private ItemStackRequestActionType actionType;

  private final List<ItemStackRequestNetworkItemInstanceDescriptor> craftResults = new ObjectArrayList<>();

  private int numCrafts;
}

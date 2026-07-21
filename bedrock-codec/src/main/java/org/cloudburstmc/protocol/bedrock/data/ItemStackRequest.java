package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.Object;
import java.lang.String;
import java.util.List;
import lombok.Data;

@Data
public class ItemStackRequest {
  private ItemStackRequestId clientRequestId;

  private final List<Object> actions = new ObjectArrayList<>();

  private final List<String> stringsToFilter = new ObjectArrayList<>();

  private TextProcessingEventOrigin stringsToFilterOrigin;
}

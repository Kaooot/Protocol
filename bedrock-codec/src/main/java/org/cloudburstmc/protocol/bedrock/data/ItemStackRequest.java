package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.Object;
import java.lang.String;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemStackRequest {
  private ItemStackRequestId clientRequestId;

  private final List<Object> actions = new ObjectArrayList<>();

  private final List<String> stringsToFilter = new ObjectArrayList<>();

  private TextProcessingEventOrigin stringsToFilterOrigin;
}

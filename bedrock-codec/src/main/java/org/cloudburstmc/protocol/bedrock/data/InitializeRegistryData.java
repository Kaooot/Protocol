package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.Data;

@Data
public class InitializeRegistryData {
  private final List<WorldClockData> clockData = new ObjectArrayList<>();
}

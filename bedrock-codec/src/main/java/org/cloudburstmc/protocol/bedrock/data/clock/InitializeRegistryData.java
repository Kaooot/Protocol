package org.cloudburstmc.protocol.bedrock.data.clock;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.WorldClockData;

@Data
public class InitializeRegistryData {
  private final List<WorldClockData> clockData = new ObjectArrayList<>();
}

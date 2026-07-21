package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Experiments {
  private final List<ExperimentToggle> toggles = new ObjectArrayList<>();

  private boolean experimentsEverToggled;
}

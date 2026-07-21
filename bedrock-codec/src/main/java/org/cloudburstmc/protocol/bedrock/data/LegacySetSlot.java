package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.Integer;
import java.util.List;
import lombok.Data;

@Data
public class LegacySetSlot {
  private ContainerEnumName containerEnum;

  private final List<Integer> slots = new ObjectArrayList<>();
}

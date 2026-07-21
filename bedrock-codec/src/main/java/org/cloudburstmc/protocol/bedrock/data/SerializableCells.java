package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.Integer;
import java.util.List;
import lombok.Data;

@Data
public class SerializableCells {
  private int xSize;

  private int ySize;

  private int zSize;

  private final List<Integer> storage = new ObjectArrayList<>();
}

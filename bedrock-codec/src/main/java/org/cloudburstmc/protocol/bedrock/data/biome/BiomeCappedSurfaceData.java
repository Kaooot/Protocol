package org.cloudburstmc.protocol.bedrock.data.biome;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.Integer;
import java.util.List;
import lombok.Data;

@Data
public class BiomeCappedSurfaceData {
  private final List<Integer> floorBlocks = new ObjectArrayList<>();

  private final List<Integer> ceilingBlocks = new ObjectArrayList<>();

  private int seaBlock;

  private int foundationBlock;

  private int beachBlock;
}

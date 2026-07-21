package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.Integer;
import java.util.List;
import lombok.Data;

@Data
public class SubChunkHeightmapData {
  private HeightMapType heightMapType;

  private final List<List<Integer>> subchunkHeightMap = new ObjectArrayList<>();

  private HeightMapDataType renderHeightMapType;

  private final List<List<Integer>> subchunkRenderHeightMap = new ObjectArrayList<>();
}

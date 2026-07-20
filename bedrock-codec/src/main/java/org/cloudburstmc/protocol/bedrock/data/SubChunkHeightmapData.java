package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.Integer;
import java.lang.String;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubChunkHeightmapData {
  private String heightMapType;

  private final List<List<Integer>> subchunkHeightMap = new ObjectArrayList<>();

  private String renderHeightMapType;

  private final List<List<Integer>> subchunkRenderHeightMap = new ObjectArrayList<>();
}

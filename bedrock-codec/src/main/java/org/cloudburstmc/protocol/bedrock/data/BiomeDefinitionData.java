package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BiomeDefinitionData {
  private int id;

  private float temperature;

  private float downfall;

  private float foliageSnow;

  private float depth;

  private float scale;

  private int mapWaterColorArgb;

  private boolean rain;

  private BiomeTagsData tags;

  private BiomeDefinitionChunkGenData chunkGenData;
}

package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
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

package org.cloudburstmc.protocol.bedrock.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BiomeDefinitionData {
  private int id;

  private float temperature;

  private float downfall;

  private float foliagesnow;

  private float depth;

  private float scale;

  private int mapwatercolorARGB;

  private boolean rain;

  private BiomeTagsData tags;

  private BiomeDefinitionChunkGenData chunkgendata;
}

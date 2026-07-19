package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.Integer;
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
public class BiomeReplacementData {
  private int replacementbiome;

  private int dimension;

  private final List<Integer> targetbiomes = new ObjectArrayList<>();

  private float amount;

  private float noisefrequencyscale;

  private int replacementindex;
}

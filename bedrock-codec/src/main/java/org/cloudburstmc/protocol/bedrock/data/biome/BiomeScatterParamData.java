package org.cloudburstmc.protocol.bedrock.data.biome;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.CoordinateEvaluationOrder;

@Data
public class BiomeScatterParamData {
  private final List<BiomeCoordinateData> coordinates = new ObjectArrayList<>();

  private CoordinateEvaluationOrder evalOrder;

  private int chancePercentType;

  private int chancePercent;

  private int chanceNumerator;

  private int chanceDenominator;

  private int iterationsType;

  private int iterations;
}

package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

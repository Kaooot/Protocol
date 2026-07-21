package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;

@Data
public class FeatureRegistryFeatureBinaryJsonFormat {
  private String featureName;

  private String binaryJsonOutput;
}

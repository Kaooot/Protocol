package org.cloudburstmc.protocol.bedrock.data.world;

import lombok.Data;

@Data
public class FeatureRegistryFeatureBinaryJsonFormat {

    private String featureName;
    private String binaryJsonOutput;
}
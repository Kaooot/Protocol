package org.cloudburstmc.protocol.bedrock.data.biome;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class BiomeConsolidatedFeatureData {
    BiomeScatterParamData scatter;
    short feature;
    short identifier;
    short pass;
    boolean canUseInternalFeature;
}
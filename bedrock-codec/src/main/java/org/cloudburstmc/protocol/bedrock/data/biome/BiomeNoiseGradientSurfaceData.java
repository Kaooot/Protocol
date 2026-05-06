package org.cloudburstmc.protocol.bedrock.data.biome;

import lombok.Value;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;

import java.util.List;

/**
 * @author Kaooot
 */
@Value
public class BiomeNoiseGradientSurfaceData {

    List<BlockDefinition> nonReplaceableBlocks;
    List<SerializedNoiseBlockSpecifier> gradientBlocks;
    NoiseDescriptor noise;
}
package org.cloudburstmc.protocol.bedrock.data.biome;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.structure.NoiseDescriptor;
import org.cloudburstmc.protocol.bedrock.data.structure.SerializedNoiseBlockSpecifier;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BiomeNoiseGradientSurfaceData {

    private final List<BlockDefinition> nonreplaceableBlocks = new ObjectArrayList<>();
    private final List<SerializedNoiseBlockSpecifier> gradientBlocks = new ObjectArrayList<>();
    private NoiseDescriptor noise;
}
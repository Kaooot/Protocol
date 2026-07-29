package org.cloudburstmc.protocol.bedrock.data.biome;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.Integer;
import java.util.List;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.NoiseDescriptor;
import org.cloudburstmc.protocol.bedrock.data.SerializedNoiseBlockSpecifier;

@Data
public class BiomeNoiseGradientSurfaceData {
  private final List<Integer> nonreplaceableBlocks = new ObjectArrayList<>();

  private final List<SerializedNoiseBlockSpecifier> gradientBlocks = new ObjectArrayList<>();

  private NoiseDescriptor noise;
}

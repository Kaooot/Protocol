package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.Integer;
import java.util.List;
import lombok.Data;

@Data
public class BiomeNoiseGradientSurfaceData {
  private final List<Integer> nonreplaceableBlocks = new ObjectArrayList<>();

  private final List<SerializedNoiseBlockSpecifier> gradientBlocks = new ObjectArrayList<>();

  private NoiseDescriptor noise;
}

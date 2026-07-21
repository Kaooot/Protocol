package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.Float;
import java.lang.String;
import java.util.List;
import lombok.Data;

@Data
public class NoiseDescriptor {
  private String name;

  private int firstOctave;

  private final List<Float> amplitudes = new ObjectArrayList<>();
}

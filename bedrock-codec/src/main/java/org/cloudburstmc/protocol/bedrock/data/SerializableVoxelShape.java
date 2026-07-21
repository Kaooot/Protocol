package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.Float;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SerializableVoxelShape {
  private SerializableCells cells;

  private final List<Float> xCoordinates = new ObjectArrayList<>();

  private final List<Float> yCoordinates = new ObjectArrayList<>();

  private final List<Float> zCoordinates = new ObjectArrayList<>();
}

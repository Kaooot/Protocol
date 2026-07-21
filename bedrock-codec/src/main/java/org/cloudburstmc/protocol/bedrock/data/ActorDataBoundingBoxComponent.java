package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.Float;
import java.util.List;
import lombok.Data;

@Data
public class ActorDataBoundingBoxComponent {
  private final List<Float> actorDataBoundingBox = new ObjectArrayList<>();
}

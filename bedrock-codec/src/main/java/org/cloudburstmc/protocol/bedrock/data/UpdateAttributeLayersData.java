package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.Data;

@Data
public class UpdateAttributeLayersData {
  private final List<AttributeLayerData> attributeLayers = new ObjectArrayList<>();
}

package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.attribute.AttributeLayerData;

@Data
public class UpdateAttributeLayersData {
  private final List<AttributeLayerData> attributeLayers = new ObjectArrayList<>();
}

package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.String;
import java.util.List;
import lombok.Data;

@Data
public class RemoveEnvironmentAttributesData {
  private String attributeLayerName;

  private DimensionType attributeLayerDimension;

  private final List<String> attributes = new ObjectArrayList<>();
}

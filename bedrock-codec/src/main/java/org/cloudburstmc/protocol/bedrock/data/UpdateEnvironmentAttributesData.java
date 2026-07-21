package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.String;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEnvironmentAttributesData {
  private String attributeLayerName;

  private DimensionType attributeLayerDimension;

  private final List<EnvironmentAttributeData> attributes = new ObjectArrayList<>();
}

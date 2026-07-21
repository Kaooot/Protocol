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
public class AttributeLayerData {
  private String name;

  private String noiseName;

  private DimensionType dimension;

  private AttributeLayerSettings settings;

  private final List<EnvironmentAttributeData> attributes = new ObjectArrayList<>();
}

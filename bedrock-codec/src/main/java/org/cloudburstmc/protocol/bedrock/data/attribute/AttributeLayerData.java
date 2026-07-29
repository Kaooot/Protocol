package org.cloudburstmc.protocol.bedrock.data.attribute;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.String;
import java.util.List;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.DimensionType;
import org.cloudburstmc.protocol.bedrock.data.EnvironmentAttributeData;

@Data
public class AttributeLayerData {
  private String name;

  private String noiseName;

  private DimensionType dimension;

  private AttributeLayerSettings settings;

  private final List<EnvironmentAttributeData> attributes = new ObjectArrayList<>();
}

package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.attribute.AttributeLayerSettings;

@Data
public class UpdateAttributeLayerSettingsData {
  private String attributeLayerName;

  private DimensionType attributeLayerDimension;

  private AttributeLayerSettings attributesLayerSettings;
}

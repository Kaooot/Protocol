package org.cloudburstmc.protocol.bedrock.data;

import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAttributeLayerSettingsData {
  private String attributeLayerName;

  private DimensionType attributeLayerDimension;

  private AttributeLayerSettings attributesLayerSettings;
}

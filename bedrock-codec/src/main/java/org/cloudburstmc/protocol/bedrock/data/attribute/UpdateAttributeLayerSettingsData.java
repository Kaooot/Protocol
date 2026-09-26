package org.cloudburstmc.protocol.bedrock.data.attribute;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.world.DimensionType;

@Data
public class UpdateAttributeLayerSettingsData {

    private String attributeLayerName;
    private DimensionType attributeLayerDimension;
    private AttributeLayerSettings attributesLayerSettings;
}
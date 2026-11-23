package org.cloudburstmc.protocol.bedrock.data.attribute;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.world.DimensionType;

@Data
public class UpdateAttributeLayerSettingsData implements ClientboundAttributeLayerSyncData {

    @Override
    public ClientboundAttributeLayerSyncDataType getType() {
        return ClientboundAttributeLayerSyncDataType.UPDATE_ATTRIBUTE_LAYER_SETTINGS;
    }


    private String attributeLayerName;
    private DimensionType attributeLayerDimension;
    private AttributeLayerSettings attributesLayerSettings;
}
package org.cloudburstmc.protocol.bedrock.data.payload.attribute;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas.AttributeLayerSettings;
import org.cloudburstmc.protocol.bedrock.data.payload.common.DimensionType;

/**
 * @author Kaooot
 */
@Data
public class UpdateAttributeLayerSettingsData implements AttributeLayerSyncPacketData {

    private String attributeLayerName;
    private DimensionType attributeLayerDimension;
    private AttributeLayerSettings attributesLayerSettings;

    @Override
    public Type getType() {
        return Type.UPDATE_ATTRIBUTE_LAYER_SETTINGS;
    }
}
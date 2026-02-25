package org.cloudburstmc.protocol.bedrock.data.payload.attribute;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.Dimension;
import org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas.AttributeLayerSettings;

/**
 * @author Kaooot
 */
@Data
public class UpdateAttributeLayerSettingsData implements AttributeLayerSyncPacketData {

    private String attributeLayerName;
    private Dimension attributeLayerDimension;
    private AttributeLayerSettings attributesLayerSettings;

    @Override
    public Type getType() {
        return Type.UPDATE_ATTRIBUTE_LAYER_SETTINGS;
    }
}
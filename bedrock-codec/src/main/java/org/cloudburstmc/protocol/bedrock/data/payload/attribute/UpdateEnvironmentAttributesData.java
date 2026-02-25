package org.cloudburstmc.protocol.bedrock.data.payload.attribute;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.Dimension;
import org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas.EnvironmentAttributeData;

import java.util.List;

/**
 * @author Kaooot
 */
@Data
public class UpdateEnvironmentAttributesData implements AttributeLayerSyncPacketData{

    private String attributeLayerName;
    private Dimension attributeLayerDimension;
    private final List<EnvironmentAttributeData> attributes = new ObjectArrayList<>();

    @Override
    public Type getType() {
        return Type.UPDATE_ENVIRONMENT_ATTRIBUTES;
    }
}
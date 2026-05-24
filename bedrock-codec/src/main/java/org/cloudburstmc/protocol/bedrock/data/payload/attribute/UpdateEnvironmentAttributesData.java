package org.cloudburstmc.protocol.bedrock.data.payload.attribute;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas.EnvironmentAttributeData;
import org.cloudburstmc.protocol.bedrock.data.payload.common.DimensionType;

import java.util.List;

/**
 * @author Kaooot
 */
@Data
public class UpdateEnvironmentAttributesData implements AttributeLayerSyncPacketData{

    private String attributeLayerName;
    private DimensionType attributeLayerDimension;
    private final List<EnvironmentAttributeData> attributes = new ObjectArrayList<>();

    @Override
    public Type getType() {
        return Type.UPDATE_ENVIRONMENT_ATTRIBUTES;
    }
}
package org.cloudburstmc.protocol.bedrock.data.attribute;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.world.DimensionType;

import java.util.List;

@Data
public class RemoveEnvironmentAttributesData implements ClientboundAttributeLayerSyncData {

    @Override
    public ClientboundAttributeLayerSyncDataType getType() {
        return ClientboundAttributeLayerSyncDataType.REMOVE_ENVIRONMENT_ATTRIBUTES;
    }


    private String attributeLayerName;
    private DimensionType attributeLayerDimension;
    private final List<String> attributes = new ObjectArrayList<>();
}
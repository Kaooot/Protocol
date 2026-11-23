package org.cloudburstmc.protocol.bedrock.data.attribute;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;

import java.util.List;

@Data
public class UpdateAttributeLayersData implements ClientboundAttributeLayerSyncData {

    @Override
    public ClientboundAttributeLayerSyncDataType getType() {
        return ClientboundAttributeLayerSyncDataType.UPDATE_ATTRIBUTE_LAYERS;
    }


    private final List<AttributeLayerData> attributeLayers = new ObjectArrayList<>();
}
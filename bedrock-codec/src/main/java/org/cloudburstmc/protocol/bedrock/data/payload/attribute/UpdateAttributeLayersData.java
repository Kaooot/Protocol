package org.cloudburstmc.protocol.bedrock.data.payload.attribute;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas.AttributeLayerData;

import java.util.List;

/**
 * @author Kaooot
 */
@Data
public class UpdateAttributeLayersData implements AttributeLayerSyncPacketData {

    private final List<AttributeLayerData> attributeLayers = new ObjectArrayList<>();

    @Override
    public Type getType() {
        return Type.UPDATE_ATTRIBUTE_LAYERS;
    }
}
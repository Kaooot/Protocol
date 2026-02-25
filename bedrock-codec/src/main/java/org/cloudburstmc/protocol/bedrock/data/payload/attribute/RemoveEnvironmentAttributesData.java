package org.cloudburstmc.protocol.bedrock.data.payload.attribute;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.Dimension;

import java.util.List;

/**
 * @author Kaooot
 */
@Data
public class RemoveEnvironmentAttributesData implements AttributeLayerSyncPacketData{

    private String attributeLayerName;
    private Dimension attributeLayerDimension;
    private final List<String> attributes = new ObjectArrayList<>();

    @Override
    public Type getType() {
        return Type.REMOVE_ENVIRONMENT_ATTRIBUTES;
    }
}
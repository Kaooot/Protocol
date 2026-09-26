package org.cloudburstmc.protocol.bedrock.data.attribute;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.world.DimensionType;

import java.util.List;

@Data
public class RemoveEnvironmentAttributesData {

    private String attributeLayerName;
    private DimensionType attributeLayerDimension;
    private final List<String> attributes = new ObjectArrayList<>();
}
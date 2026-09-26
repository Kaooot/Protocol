package org.cloudburstmc.protocol.bedrock.data.attribute;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.world.DimensionType;

import java.util.List;

@Data
public class UpdateEnvironmentAttributesData {

    private String attributeLayerName;
    private DimensionType attributeLayerDimension;
    private final List<EnvironmentAttributeData> attributes = new ObjectArrayList<>();
}
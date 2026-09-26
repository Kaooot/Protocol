package org.cloudburstmc.protocol.bedrock.data.attribute;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;

import java.util.List;

@Data
public class UpdateAttributeLayersData {

    private final List<AttributeLayerData> attributeLayers = new ObjectArrayList<>();
}
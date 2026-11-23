package org.cloudburstmc.protocol.bedrock.data.attribute;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.bedrock.annotation.Removed;
import org.cloudburstmc.protocol.bedrock.data.world.DimensionType;

import java.util.List;

@Data
public class AttributeLayerData {

    private String name;
    @Added("26.30")
    @Deprecated
    @Removed("26.60")
    private String noiseName;
    private DimensionType dimension;
    private AttributeLayerSettings settings;
    private final List<EnvironmentAttributeData> attributes = new ObjectArrayList<>();
}
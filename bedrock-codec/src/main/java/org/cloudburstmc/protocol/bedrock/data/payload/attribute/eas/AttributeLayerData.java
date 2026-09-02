package org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.payload.common.DimensionType;

import java.util.List;

/**
 * @author Kaooot
 */
@Data
public class AttributeLayerData {

    private String name;
    /**
     * @since v1001
     * @deprecated since v2207
     */
    private String noiseName;
    private DimensionType dimension;
    private AttributeLayerSettings settings;
    private final List<EnvironmentAttributeData> attributes = new ObjectArrayList<>();
}
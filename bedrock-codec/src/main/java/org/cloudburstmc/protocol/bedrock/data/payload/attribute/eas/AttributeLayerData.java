package org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.Dimension;

import java.util.List;

/**
 * @author Kaooot
 */
@Data
public class AttributeLayerData {

    private String name;
    private Dimension dimension;
    private AttributeLayerSettings settings;
    private final List<EnvironmentAttributeData> attributes = new ObjectArrayList<>();
}
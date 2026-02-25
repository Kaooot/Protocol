package org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.camera.EasingType;

/**
 * @author Kaooot
 */
@Data
public class EnvironmentAttributeData {

    private String attributeName;
    private AttributeData fromAttribute;
    private AttributeData attribute;
    private AttributeData toAttribute;
    private int currentTransitionTicks;
    private int totalTransitionTicks;
    private EasingType easing;
}
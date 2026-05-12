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
    /**
     * @since v998
     */
    private int localTransitionTicks;
    /**
     * @since v998
     */
    private boolean noiseTransition;
}
package org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.camera.EasingType;

/**
 * @author Kaooot
 */
@Data
public class EnvironmentAttributeData {

    private String attributeName;
    private EASAttributeData fromAttribute;
    private EASAttributeData attribute;
    private EASAttributeData toAttribute;
    private int currentTransitionTicks;
    private int totalTransitionTicks;
    private EasingType easing;
    /**
     * @since v1001
     */
    private int localTransitionTicks;
    /**
     * @since v1001
     */
    private boolean noiseTransition;
    /**
     * @since v2187
     */
    private NoiseAlignment noiseAlignment;
}
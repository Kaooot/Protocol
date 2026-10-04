package org.cloudburstmc.protocol.bedrock.data.attribute;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.camera.EasingFunction;
import org.cloudburstmc.protocol.bedrock.data.structure.NoiseAlignment;

@Data
public class EnvironmentAttributeData {

    private String attributeName;

    private Object fromAttribute;

    private Object attribute;

    private Object toAttribute;

    private int currentTransitionTicks;

    private int totalTransitionTicks;

    private EasingFunction easing;

    /**
     * @since v1001
     */
    private int localTransitionTicks;

    /**
     * @since v1001
     */
    private boolean noiseTransition;

    /**
     * @since v2193
     */
    private NoiseAlignment noiseAlignment;
}

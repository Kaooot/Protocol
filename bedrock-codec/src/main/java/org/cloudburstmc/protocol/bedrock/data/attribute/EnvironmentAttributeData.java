package org.cloudburstmc.protocol.bedrock.data.attribute;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.camera.EasingFunction;
import org.cloudburstmc.protocol.bedrock.data.structure.NoiseAlignment;

@Data
public class EnvironmentAttributeData {

    private String attributeName;
    /**
     * @deprecated since v2225
     */
    private Object fromAttribute;
    /**
     * @deprecated since v2225
     */
    private Object attribute;
    /**
     * @deprecated since v2225
     */
    private Object toAttribute;
    /**
     * @deprecated since v2225
     */
    private int currentTransitionTicks;
    /**
     * @deprecated since v2225
     */
    private int totalTransitionTicks;
    /**
     * @deprecated since v2225
     */
    private EasingFunction easing;
    /**
     * @since v1001
     * @deprecated since v2225
     */
    private int localTransitionTicks;
    /**
     * @since v1001
     * @deprecated since v2225
     */
    private boolean noiseTransition;
    /**
     * @since v2193
     * @deprecated since v2225
     */
    private NoiseAlignment noiseAlignment;
    /**
     * @since v2225
     */
    private Object payload;
}
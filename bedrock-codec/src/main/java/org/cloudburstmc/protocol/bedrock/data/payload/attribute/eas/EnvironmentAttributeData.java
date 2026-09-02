package org.cloudburstmc.protocol.bedrock.data.payload.attribute.eas;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.camera.EasingType;

/**
 * @author Kaooot
 */
@Data
public class EnvironmentAttributeData {

    private String attributeName;

    // --- Legacy flat representation (< v2207). Kept for backwards compatibility with older codecs. ---

    /**
     * @deprecated since v2207, replaced by {@link #payload}. Still used by pre-v2207 codecs.
     */
    @Deprecated
    private EASAttributeData fromAttribute;
    /**
     * @deprecated since v2207, replaced by {@link #payload}. Still used by pre-v2207 codecs.
     */
    @Deprecated
    private EASAttributeData attribute;
    /**
     * @deprecated since v2207, replaced by {@link #payload}. Still used by pre-v2207 codecs.
     */
    @Deprecated
    private EASAttributeData toAttribute;
    /**
     * @deprecated since v2207, replaced by {@link #payload}. Still used by pre-v2207 codecs.
     */
    @Deprecated
    private int currentTransitionTicks;
    /**
     * @deprecated since v2207, replaced by {@link #payload}. Still used by pre-v2207 codecs.
     */
    @Deprecated
    private int totalTransitionTicks;
    /**
     * @deprecated since v2207, replaced by {@link #payload}. Still used by pre-v2207 codecs.
     */
    @Deprecated
    private EasingType easing;
    /**
     * @since v1001
     * @deprecated since v2207, replaced by {@link #payload}. Still used by pre-v2207 codecs.
     */
    @Deprecated
    private int localTransitionTicks;
    /**
     * @since v1001
     * @deprecated since v2207, replaced by {@link #payload}. Still used by pre-v2207 codecs.
     */
    @Deprecated
    private boolean noiseTransition;
    /**
     * @since v2192
     * @deprecated since v2207, replaced by {@link #payload}. Still used by pre-v2207 codecs.
     */
    @Deprecated
    private NoiseAlignment noiseAlignment;

    // --- Structured representation (v2207+). ---

    /**
     * One of {@link ConstantAttributeData}, {@link TransitionAttributeData} or
     * {@link NoiseTransitionAttributeData}.
     *
     * @since v2207
     */
    private EnvironmentAttributePayload payload;
}
package org.cloudburstmc.protocol.bedrock.data.attribute;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.annotation.Added;
import org.cloudburstmc.protocol.bedrock.annotation.Removed;
import org.cloudburstmc.protocol.bedrock.data.camera.EasingFunction;
import org.cloudburstmc.protocol.bedrock.data.structure.NoiseAlignment;

@Data
public class EnvironmentAttributeData {

    private String attributeName;
    @Deprecated
    @Removed("26.60")
    private Object fromAttribute;
    @Deprecated
    @Removed("26.60")
    private Object attribute;
    @Deprecated
    @Removed("26.60")
    private Object toAttribute;
    @Deprecated
    @Removed("26.60")
    private int currentTransitionTicks;
    @Deprecated
    @Removed("26.60")
    private int totalTransitionTicks;
    @Deprecated
    @Removed("26.60")
    private EasingFunction easing;
    @Added("26.30")
    @Deprecated
    @Removed("26.60")
    private int localTransitionTicks;
    @Added("26.30")
    @Deprecated
    @Removed("26.60")
    private boolean noiseTransition;
    @Added("26.50")
    @Deprecated
    @Removed("26.60")
    private NoiseAlignment noiseAlignment;
    @Added("26.60")
    private Object payload;
}
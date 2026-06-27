package org.cloudburstmc.protocol.bedrock.data.payload.skin;

import lombok.Data;

/**
 * @author Kaooot
 */
@Data
public class AnimatedImageData {

    private SkinImage skinImage;
    private AnimatedTextureType animatedTextureType;
    private float frames;
    private AnimationExpression animationExpression;
}
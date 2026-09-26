package org.cloudburstmc.protocol.bedrock.data.skin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimatedImageData {

    private SkinImage skinImage;
    private PersonaAnimatedTextureType animatedTextureType;
    private float frames;
    private PersonaAnimationExpression animationExpression;

    public AnimatedImageData(SkinImage skinImage, PersonaAnimatedTextureType animatedTextureType, float frames) {
        this.skinImage = skinImage;
        this.animatedTextureType = animatedTextureType;
        this.frames = frames;
    }
}
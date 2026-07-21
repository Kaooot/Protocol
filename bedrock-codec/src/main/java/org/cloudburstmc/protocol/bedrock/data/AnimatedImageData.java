package org.cloudburstmc.protocol.bedrock.data;

import lombok.Data;

@Data
public class AnimatedImageData {
  private SkinImage skinImage;

  private PersonaAnimatedTextureType animatedTextureType;

  private float frames;

  private PersonaAnimationExpression animationExpression;
}

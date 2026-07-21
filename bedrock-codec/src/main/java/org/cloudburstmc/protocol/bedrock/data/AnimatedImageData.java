package org.cloudburstmc.protocol.bedrock.data;

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
}

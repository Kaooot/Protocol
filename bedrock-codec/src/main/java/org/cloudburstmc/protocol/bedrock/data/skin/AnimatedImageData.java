package org.cloudburstmc.protocol.bedrock.data.skin;

import lombok.Data;
import org.cloudburstmc.protocol.bedrock.data.PersonaAnimatedTextureType;
import org.cloudburstmc.protocol.bedrock.data.PersonaAnimationExpression;
import org.cloudburstmc.protocol.bedrock.data.SkinImage;

@Data
public class AnimatedImageData {
  private SkinImage skinImage;

  private PersonaAnimatedTextureType animatedTextureType;

  private float frames;

  private PersonaAnimationExpression animationExpression;
}

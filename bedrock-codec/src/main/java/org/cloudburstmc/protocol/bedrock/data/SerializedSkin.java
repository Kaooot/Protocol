package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.awt.Color;
import java.lang.String;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SerializedSkin {
  private String ID;

  private String playFabID;

  private String resourcePatch;

  private SkinImage imageData;

  private final List<AnimatedImageData> animatedImageData = new ObjectArrayList<>();

  private SkinImage capeImageData;

  private String geometryData;

  private String geometryDataMinEngineVersion;

  private String animationData;

  private String capeID;

  private String fullID;

  private PersonaArmSizeType armSize;

  private Color skinColor;

  private final List<SerializedPersonaPieceHandle> personaPieces = new ObjectArrayList<>();

  private final Map<PersonaPieceType, TintMapColor> pieceTintColors = new HashMap<>();

  private boolean isPremium;

  private boolean isPersona;

  private boolean isPersonaCapeOnClassicSkin;

  private boolean isPrimaryUser;

  private boolean overridesPlayerAppearance;

  private TrustedSkinFlag trustedSkinFlag;

  private String profileHash;
}

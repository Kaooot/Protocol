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

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SerializedSkin {
  private String ID;

  private String PlayFabID;

  private String ResourcePatch;

  private SkinImage ImageData;

  private final List<AnimatedImageData> AnimatedImageData = new ObjectArrayList<>();

  private SkinImage CapeImageData;

  private String JsonValue;

  private String MinEngineVersion;

  private String AnimationData;

  private String CapeID;

  private String FullID;

  private personaArmSizeType ArmSize;

  private Color SkinColor;

  private final List<SerializedPersonaPieceHandle> PersonaPieces = new ObjectArrayList<>();

  private final Map<personaPieceType, TintMapColor> PieceTintColors = new HashMap<>();

  private boolean IsPremium;

  private boolean IsPersona;

  private boolean IsPersonaCapeOnClassicSkin;

  private boolean IsPrimaryUser;

  private boolean OverridesPlayerAppearance;

  private TrustedSkinFlag TrustedSkinFlag;

  private String ProfileHash;
}

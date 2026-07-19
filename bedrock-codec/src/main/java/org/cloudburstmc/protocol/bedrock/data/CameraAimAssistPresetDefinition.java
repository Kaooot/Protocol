package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
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
public class CameraAimAssistPresetDefinition {
  private String CameraAimAssistIdentifier;

  private CameraAimAssistPresetExclusionDefinition exclusionsettings;

  private final List<String> liquidtargetinglist = new ObjectArrayList<>();

  private final Map<String, String> itemsettings = new HashMap<>();

  private String CameraAimAssistCategoryReference;

  private String CameraAimAssistCategoryReference;
}

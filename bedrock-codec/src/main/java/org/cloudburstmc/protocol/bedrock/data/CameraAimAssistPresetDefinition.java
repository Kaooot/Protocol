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
  private String cameraAimAssistIdentifier;

  private CameraAimAssistPresetExclusionDefinition exclusionSettings;

  private final List<String> liquidTargetingList = new ObjectArrayList<>();

  private final Map<String, String> itemSettings = new HashMap<>();

  private String cameraAimAssistCategoryReference;

  private String cameraAimAssistCategoryReference;
}

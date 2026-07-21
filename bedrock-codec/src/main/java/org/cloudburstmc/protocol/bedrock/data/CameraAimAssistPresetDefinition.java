package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.String;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class CameraAimAssistPresetDefinition {
  private String identifier;

  private CameraAimAssistPresetExclusionDefinition exclusionSettings;

  private final List<String> liquidTargetingList = new ObjectArrayList<>();

  private final Map<String, String> itemSettings = new HashMap<>();

  private String defaultItemSettings;

  private String handSettings;
}

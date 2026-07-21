package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.String;
import java.util.List;
import lombok.Data;

@Data
public class CameraAimAssistPresetExclusionDefinition {
  private final List<String> blocks = new ObjectArrayList<>();

  private final List<String> entities = new ObjectArrayList<>();

  private final List<String> blockTags = new ObjectArrayList<>();

  private final List<String> entityTypeFamilies = new ObjectArrayList<>();
}

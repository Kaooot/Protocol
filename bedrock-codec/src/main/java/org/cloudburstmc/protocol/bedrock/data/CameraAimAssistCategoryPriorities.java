package org.cloudburstmc.protocol.bedrock.data;

import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;

@Data
public class CameraAimAssistCategoryPriorities {
  private final Map<String, Integer> entities = new HashMap<>();

  private final Map<String, Integer> blocks = new HashMap<>();

  private Object blockTags;

  private Object entityTypeFamilies;

  private Integer entityDefault;

  private Integer blockDefault;
}

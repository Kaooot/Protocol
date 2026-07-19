package org.cloudburstmc.protocol.bedrock.data;

import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import java.util.HashMap;
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
public class CameraAimAssistCategoryPriorities {
  private final Map<String, Integer> entities = new HashMap<>();

  private final Map<String, Integer> blocks = new HashMap<>();

  private Object blocktags;

  private Object entitytypefamilies;

  private Integer entitydefault;

  private Integer blockdefault;
}

package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.String;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttributeData {
  private float minValue;

  private float maxValue;

  private float currentValue;

  private float defaultMinValue;

  private float defaultMaxValue;

  private float defaultValue;

  private String hashedString;

  private final List<AttributeModifier> modifiers = new ObjectArrayList<>();
}

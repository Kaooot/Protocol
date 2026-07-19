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
  private float MinValue;

  private float MaxValue;

  private float CurrentValue;

  private float DefaultMinValue;

  private float DefaultMaxValue;

  private float DefaultValue;

  private String hashedstring;

  private final List<AttributeModifier> Modifiers = new ObjectArrayList<>();
}

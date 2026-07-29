package org.cloudburstmc.protocol.bedrock.data.actor.attribute;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.lang.String;
import java.util.List;
import lombok.Data;

@Data
public class AttributeData {
  private float minValue;

  private float maxValue;

  private float currentValue;

  private float defaultMinValue;

  private float defaultMaxValue;

  private float defaultValue;

  private String name;

  private final List<AttributeModifier> modifiers = new ObjectArrayList<>();
}

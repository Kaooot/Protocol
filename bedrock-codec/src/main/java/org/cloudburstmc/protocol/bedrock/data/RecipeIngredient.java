package org.cloudburstmc.protocol.bedrock.data;

import java.lang.Object;
import lombok.Data;

@Data
public class RecipeIngredient {
  private Object itemDescriptor;

  private int stackSize;
}

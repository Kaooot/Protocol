package org.cloudburstmc.protocol.bedrock.data;

import java.lang.Object;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeIngredient {
  private Object itemDescriptor;

  private int stackSize;
}

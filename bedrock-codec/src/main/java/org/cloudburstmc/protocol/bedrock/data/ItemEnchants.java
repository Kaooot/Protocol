package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ItemEnchants {
  private int slot;

  private final List<List<EnchantmentInstance>> itemEnchants = new ObjectArrayList<>();
}

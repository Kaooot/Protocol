package org.cloudburstmc.protocol.bedrock.data;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.awt.Color;
import java.util.List;
import lombok.Data;

@Data
public class TintMapColor {
  private final List<Color> colors = new ObjectArrayList<>();
}

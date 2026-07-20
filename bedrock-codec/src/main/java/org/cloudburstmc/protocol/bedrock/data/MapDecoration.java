package org.cloudburstmc.protocol.bedrock.data;

import java.awt.Color;
import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Auto generated from 1.26.40-beta.31 (v2168)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MapDecoration {
  private MapDecorationType imageType;

  private int rotation;

  private int x;

  private int y;

  private String label;

  private Color color;
}

package org.cloudburstmc.protocol.bedrock.data;

import java.awt.Color;
import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cloudburstmc.math.vector.Vector3f;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DebugMarkerData {
  private String text;

  private Vector3f position;

  private Color color;

  private long duration;
}

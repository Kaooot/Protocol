package org.cloudburstmc.protocol.bedrock.data;

import java.lang.Float;
import java.lang.Integer;
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
public class SerializedNoiseBlockSpecifier {
  private String noise;

  private Float threshold;

  private FloatRange range;

  private Integer block;
}

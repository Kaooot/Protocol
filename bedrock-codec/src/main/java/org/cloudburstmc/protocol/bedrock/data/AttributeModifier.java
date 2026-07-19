package org.cloudburstmc.protocol.bedrock.data;

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
public class AttributeModifier {
  private String Id;

  private String Name;

  private float Amount;

  private int Operation;

  private int Operand;

  private boolean IsSerializable;
}
